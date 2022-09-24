package com.example.kabisa_backend.converter;

import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import com.example.kabisa_backend.model.responses.Quote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuoteConverter {

    public Quote convertToQuote(QuoteResponse quoteResponse) {
        return Quote.builder()
                .quote(quoteResponse.getQuote())
                .build();
    }
}
