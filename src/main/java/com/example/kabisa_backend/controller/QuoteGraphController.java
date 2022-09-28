package com.example.kabisa_backend.controller;

import com.example.kabisa_backend.model.entity.Quote;
import com.example.kabisa_backend.repository.QuoteRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
@Hidden
public class QuoteGraphController implements GraphQLQueryResolver {

    private final QuoteRepository quoteRepository;

    @Hidden
    public List<Quote> getAllQuotes(){
        log.info("Received request for all quotes");
        return quoteRepository.findAll();
    }
}
