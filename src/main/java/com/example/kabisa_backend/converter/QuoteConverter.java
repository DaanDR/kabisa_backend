package com.example.kabisa_backend.converter;

import com.example.kabisa_backend.model.entity.Quote;
import com.example.kabisa_backend.model.quoteapi.QuoteRequest;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuoteConverter {

    private final static int DEFAULT_LIKES = 0;

    public Quote convertToQuoteEntity(QuoteResponse quoteResponse) {
        return Quote.builder()
                .author(quoteResponse.getAuthor())
                .quote(quoteResponse.getQuote())
                .permalink(quoteResponse.getPermalink())
                .likes(DEFAULT_LIKES)
                .build();
    }

    public Quote convertQuoteRequestToQuoteEntity(QuoteRequest quoteRequest){
        return Quote.builder()
                .author(quoteRequest.getAuthor())
                .quote(quoteRequest.getQuote())
                .permalink("")
                .likes(DEFAULT_LIKES)
                .build();
    }

    public QuoteResponse convertToQuoteResponse(Quote quote){
        return QuoteResponse.builder()
                .id(quote.getId())
                .author(quote.getAuthor())
                .quote(quote.getQuote())
                .permalink(quote.getPermalink())
                .likes(quote.getLikes() == null ? 0 : quote.getLikes())
                .build();
    }
}
