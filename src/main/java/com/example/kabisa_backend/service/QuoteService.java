package com.example.kabisa_backend.service;

import com.example.kabisa_backend.client.QuoteClient;
import com.example.kabisa_backend.converter.QuoteConverter;
import com.example.kabisa_backend.model.entity.Quote;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import com.example.kabisa_backend.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<QuoteResponse> retrieveAllQuotes() {
        return quoteRepository.findAll().stream()
                .map(quoteConverter::convertToQuoteResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public String likeQuote(int id) {
       Optional<Quote> quote = quoteRepository.findById(id);
       if (quote.isPresent()){
           quote.get().setLikes(quote.get().getLikes() + 1);
           quoteRepository.save(quote.get());
           return "Quote succesfully liked";
       } else {
           return "No quote found";
       }
    }

    private QuoteResponse saveQuote(QuoteResponse quoteResponse) {
        this.quoteRepository.save(quoteConverter.convertToQuoteEntity(quoteResponse));
        log.info("Saved quote in DB");
        return quoteResponse;
    }
}
