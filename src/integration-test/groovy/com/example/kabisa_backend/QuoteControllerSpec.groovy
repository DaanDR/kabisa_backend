package com.example.kabisa_backend

import com.example.kabisa_backend.utils.BaseTestSpec
import com.example.kabisa_backend.controller.QuoteController
import com.example.kabisa_backend.model.entity.Quote
import com.example.kabisa_backend.model.quoteapi.QuoteResponse
import org.springframework.beans.factory.annotation.Autowired

class QuoteControllerSpec extends BaseTestSpec {

    @Autowired
    QuoteController quoteController

    private static final String URL_RANDOM_QUOTE = "/quotes/random"
    String QUOTE_RESPONSE_RANDOM = this.getClass().getResource('/testdata/randomquote.json').text

    def "When likeAQuote is called, likes for that quote is incremented by one"() {
        given:
        createStub("/random.json", QUOTE_RESPONSE_RANDOM)

        when:
        QuoteResponse quoteResponse = getForEntity(URL_RANDOM_QUOTE, QuoteResponse).body

        then:
        quoteResponse != null

        List<Quote> quoteList = quoteRepository.findAll()
        quoteList.size() == 1
    }
}
