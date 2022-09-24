package com.example.kabisa_backend.controller;

import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import com.example.kabisa_backend.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/quotes/")
public class QuoteController {

    private final QuoteService quoteService;


    @GetMapping("random")
    public Mono<QuoteResponse> getRandomQuote(){
        log.info("Received request for a random quote");
        return quoteService.retrieveRandomQuote();
    }

    @GetMapping("all")
    public List<QuoteResponse> getAllQuotes(){
        log.info("Received request for all quotes");
        return quoteService.retrieveAllQuotes();
    }

    @GetMapping("all")
    public String likeAQuote(int id){
        log.info("Received request to like a quote");
        return quoteService.likeQuote(id);
    }
}
