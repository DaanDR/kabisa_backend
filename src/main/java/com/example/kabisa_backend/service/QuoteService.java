package com.example.kabisa_backend.service;

import com.example.kabisa_backend.client.QuoteClient;
import com.example.kabisa_backend.converter.QuoteConverter;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import com.example.kabisa_backend.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QuoteService {

    private final QuoteClient quoteClient;
    private final QuoteRepository quoteRepository;
    private final QuoteConverter quoteConverter;

    @Autowired
    public QuoteService(QuoteClient quoteClient, QuoteRepository quoteRepository, QuoteConverter quoteConverter) {
        this.quoteClient = quoteClient;
        this.quoteRepository = quoteRepository;
        this.quoteConverter = quoteConverter;
    }

    public Mono<QuoteResponse> retrieveRandomQuote() {
        return quoteClient.getRandomQuote()
                .map(this::saveQuote)
                .doOnSuccess(quoteResponse -> log.info("Succesfully retrieved a random quote"));
    }

    private QuoteResponse saveQuote(QuoteResponse quoteResponse) {
        this.quoteRepository.save(quoteConverter.convertToQuote(quoteResponse));
        log.info("Saved quote in DB");
        return quoteResponse;
    }
}
