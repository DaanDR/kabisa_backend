package com.example.kabisa_backend.controller;

import com.example.kabisa_backend.model.responses.Quote;
import com.example.kabisa_backend.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/quotes/")
public class RandomQuoteController {

    private final QuoteService quoteService;

    @GetMapping("random")
    public Mono<Quote> getRandomQuote(){
        log.info("Received request for a random quote");
        return quoteService.retrieveRandomQuote();
    }
}
