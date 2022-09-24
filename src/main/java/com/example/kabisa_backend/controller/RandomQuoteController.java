package com.example.kabisa_backend.controller;

import com.example.kabisa_backend.client.QuoteClient;
import com.example.kabisa_backend.model.QuoteResponse;
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

    private final QuoteClient quoteClient;

    @GetMapping("random")
    public Mono<QuoteResponse> getRandomQuote(){
        log.info("Received request for a random quote");
        return quoteClient.getRandomQuote();
    }
}
