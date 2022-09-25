package com.example.kabisa_backend.converter

import com.example.kabisa_backend.TestUtils
import com.example.kabisa_backend.model.entity.Quote
import com.example.kabisa_backend.model.quoteapi.QuoteRequest
import com.example.kabisa_backend.model.quoteapi.QuoteResponse
import spock.lang.Specification

class QuoteConverterSpec extends Specification {

    def converter = new QuoteConverter()

    def "convertToQuoteEntity correctly converts QuoteResponse to Quote Entity"() {
        given:
        QuoteResponse quoteResponse = TestUtils.createQuoteResponse()

        when:
        def result = converter.convertToQuoteEntity(quoteResponse)

        then:
        result instanceof Quote
        result.getQuote() == "testQuote"
    }

    def "convertQuoteRequestToQuoteEntity correctly converts QuoteRequest to Quote Entity"() {
        given:
        QuoteRequest quoteRequest = TestUtils.createQuoteRequest()

        when:
        def result = converter.convertQuoteRequestToQuoteEntity(quoteRequest)

        then:
        result instanceof Quote
        result.getQuote() == "ownQuote"
    }

    def "convertToQuoteResponse correctly converts Quote to Quote Response"() {
        given:
        Quote quote = TestUtils.createQuote()

        when:
        def result = converter.convertToQuoteResponse(quote)

        then:
        result instanceof QuoteResponse
        result.getQuote() == "testQuote"
    }
}
