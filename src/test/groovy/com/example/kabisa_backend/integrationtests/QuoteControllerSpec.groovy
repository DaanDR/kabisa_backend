package com.example.kabisa_backend.integrationtests

import com.example.kabisa_backend.KabisaBackendApplication
import com.example.kabisa_backend.controller.QuoteController
import com.example.kabisa_backend.exception.QuoteNotFoundException
import com.example.kabisa_backend.model.entity.Quote
import com.example.kabisa_backend.model.quoteapi.QuoteResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.testcontainers.spock.Testcontainers

@Testcontainers
@Slf4j
@SpringBootTest(
        classes = [KabisaBackendApplication],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuoteControllerSpec extends BaseTestSpec {

    @Autowired
    QuoteController quoteController

    private static final String URL_RANDOM_QUOTE = "/quotes/random"
    private static final String URL_LIKE_QUOTE = "/quotes/like/1001"
    String QUOTE_RESPONSE_RANDOM = this.getClass().getResource('/testdata/randomquote.json').text

    def "When a random quote endpoint is called, a random quote is returned and saved."() {
        given: "stub for the quote service is setup"
        createStub("/random.json", QUOTE_RESPONSE_RANDOM)

        when: "Random quote endpoint is called"
        QuoteResponse quoteResponse = getForEntity(URL_RANDOM_QUOTE, QuoteResponse).body

        then: "Response is as expected"
        quoteResponse != null
        quoteResponse.getQuote() == "We should forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil."

        List<Quote> quoteList = quoteRepository.findAll()
        quoteList.size() == 1
    }

    def "When a random quote dendpoint is called, a random quote is returned and saved."() {
        given: "stub for the quote service is setup"
        createStubNotFound("/random.json")

        when: "Random quote endpoint is called"
        ResponseEntity response = getForEntity(URL_RANDOM_QUOTE, String)

        then: "Response is as expected"
        response.getBody() != null
        response.getBody() == "Quote not found"
    }

    def "When likeAQuote is called with an ID greater than 1000 a bad request is returned with informative string"() {
        when: "Like quote endpoint is called with unvalid ID"
        ResponseEntity response = getForEntity(URL_LIKE_QUOTE, String)

        then: "Response is as expected"
        response.getBody() != null
        response.getBody() == "likeAQuote.id: Parameter Id can't be larger than 1000."
        response.getStatusCodeValue() == 400
    }
}
