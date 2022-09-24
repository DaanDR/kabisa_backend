package com.example.kabisa_backend.service;

import com.example.kabisa_backend.client.QuoteClient;
import com.example.kabisa_backend.converter.QuoteConverter;
import com.example.kabisa_backend.model.responses.Quote;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class QuoteService {

    private final QuoteClient quoteClient;
    private final QuoteConverter quoteConverter;

    public Mono<Quote> retrieveRandomQuote(){
        return quoteClient.getRandomQuote()
                .map(quoteConverter::convertToQuote)
                .doOnSuccess(quote -> log.info("Succesfully retrieved and converted a random quote"));
    }
}
