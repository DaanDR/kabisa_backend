package com.example.kabisa_backend

import com.example.kabisa_backend.model.entity.Quote
import com.example.kabisa_backend.model.quoteapi.QuoteRequest
import com.example.kabisa_backend.model.quoteapi.QuoteResponse

class TestUtils {


    static QuoteRequest createQuoteRequest() {
        return QuoteRequest.builder()
                .author("testAuthor")
                .quote("ownQuote").build()
    }

    static QuoteResponse createQuoteResponse() {
        return QuoteResponse.builder()
                .id(1)
                .author("testAuthor")
                .quote("testQuote")
                .permalink("testPermalink")
                .likes(5).build()
    }

    static QuoteResponse createAnotherQuoteResponse() {
        return QuoteResponse.builder()
                .id(2)
                .author("testAuthor2")
                .quote("testQuote2")
                .permalink("testPermalink2")
                .likes(8).build()
    }

    static Quote createOwnQuote() {
        return Quote.builder()
                .id(1)
                .author("testAuthor")
                .quote("ownQuote")
                .permalink("")
                .likes(0).build()
    }

    static Quote createQuote() {
        return Quote.builder()
                .id(1)
                .author("testAuthor")
                .quote("testQuote")
                .permalink("testPermalink")
                .likes(5).build()
    }

    static Quote createAnotherQuote() {
        return Quote.builder()
                .id(2)
                .author("testAuthor2")
                .quote("testQuote2")
                .permalink("testPermalink2")
                .likes(8).build()
    }
}
