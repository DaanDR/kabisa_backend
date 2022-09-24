package com.example.kabisa_backend.converter;

import com.example.kabisa_backend.model.entity.Quote;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuoteConverter {

    public Quote convertToQuoteEntity(QuoteResponse quoteResponse) {
        return Quote.builder()
                .author(quoteResponse.getAuthor())
                .quote(quoteResponse.getQuote())
                .permalink(quoteResponse.getPermalink())
                .build();
    }

    public QuoteResponse convertToQuoteResponse(Quote quote){
        return QuoteResponse.builder()
                .id(String.valueOf(quote.getId()))
                .author(quote.getAuthor())
                .quote(quote.getQuote())
                .permalink(quote.getPermalink())
                .likes(String.valueOf(quote.getLikes())).build();
    }
}
