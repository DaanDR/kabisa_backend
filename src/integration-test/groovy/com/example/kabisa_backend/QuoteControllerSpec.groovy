package com.example.kabisa_backend


import com.example.kabisa_backend.controller.QuoteController
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
//    String QUOTE_RESPONSE_RANDOM = this.getClass().getResource('/resources/testdata/randomquote.json').text
    String QUOTE_RESPONSE_RANDOM = this.getClass().getResource('/testdata/randomquote.json').text

    def "When a random quote endpoint is called, a random quote is returned and saved."() {
        given:
        createStub("/random.json", QUOTE_RESPONSE_RANDOM)

        when:
        QuoteResponse quoteResponse = getForEntity(URL_RANDOM_QUOTE, QuoteResponse).body

        then:
        quoteResponse != null
        quoteResponse.getQuote() == "We should forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil."

        List<Quote> quoteList = quoteRepository.findAll()
        quoteList.size() == 1
    }

    def "When likeAQuote is called with an ID greater than 1000 a bad request is returned with informative string"() {
        when:
        ResponseEntity response = getForEntity(URL_LIKE_QUOTE, String)

        then:
        response.getBody() != null
        response.getBody() == "likeAQuote.id: Parameter Id can't be larger than 1000."
        response.getStatusCodeValue() == 400
    }
}
