package com.example.kabisa_backend.controller;

import com.example.kabisa_backend.model.quoteapi.QuoteRequest;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import com.example.kabisa_backend.service.QuoteService;
import com.example.kabisa_backend.validation.ValidId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/quotes/")
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("random")
    @Operation(tags = "1. Retrieve a random quote", summary = "Get a random quote that is saved in a postgres database.")
    public Mono<QuoteResponse> getRandomQuote(){
        log.info("Received request for a random quote");
        return quoteService.retrieveRandomQuote();
    }

    @GetMapping("all")
    @Operation(tags = "2. Retrieve all random quotes", summary = "Retrieves all saved random quotes.")
    public List<QuoteResponse> getAllQuotes(){
        log.info("Received request for all quotes");
        return quoteService.retrieveAllQuotes();
    }

    @GetMapping("like")
    @Operation(tags = "3. Like a random quote", summary = "Like a quote")
    public String likeAQuote(@RequestParam @ValidId Integer id){
        log.info("Received request to like a quote");
        return quoteService.likeQuote(id);
    }

    @GetMapping("all/likes")
    @Operation(tags = "4. Retrieve all quotes, ordered by likes", summary = "Retrieves all saved random quotes, ordered by amount of likes.")
    public List<QuoteResponse> getAllQuotesOrderedByLikes(){
        log.info("Received request for all quotes, ordered by likes");
        return quoteService.retrieveAllQuotesSortedByLikes();
    }

    @GetMapping("containing/{word}")
    @Operation(tags = "5. Retrieve quotes containing the given string", summary = "Retrieves all quotes containing the given string")
    public List<QuoteResponse> containingQuotes(@PathVariable String word){
        log.info("Received request to check for quotes containing the string : {}", word);
        return quoteService.containingQuotes(word);
    }

    @PostMapping("geniousquote")
    @Operation(tags = "6. Save your own quote", summary = "Save your own brilliant quote.")
    public String addMyOwnQuote(@RequestBody QuoteRequest quoteRequest){
            log.info("Received request to add a quote from someone who thinks he's smart");
            return quoteService.addMyOwnQuote(quoteRequest);
        }
}
