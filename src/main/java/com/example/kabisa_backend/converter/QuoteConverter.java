package com.example.kabisa_backend.converter;

import com.example.kabisa_backend.model.entity.Quote;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuoteConverter {

    public Quote convertToQuote(QuoteResponse quoteResponse) {
        return Quote.builder()
                .author(quoteResponse.getAuthor())
                .quote(quoteResponse.getQuote())
                .permalink(quoteResponse.getPermalink())
                .build();
    }
}
