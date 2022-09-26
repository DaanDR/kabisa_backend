package com.example.kabisa_backend.client;

import com.example.kabisa_backend.exception.QuoteNotFoundException;
import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
@Slf4j
public class QuoteClient {

    private final WebClient webClient;

    @Autowired
    protected QuoteClient(@Qualifier("quoteClientConfig") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<QuoteResponse> getRandomQuote() {
        log.info("Calling random quote api");
        return webClient.get()
                .uri("/random.json")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, handleErrorFunction())
                .bodyToMono(QuoteResponse.class);
    }

    private static Function<ClientResponse, Mono<? extends Throwable>> handleErrorFunction() {
        return clientResponse -> {
            log.error("Error Calling quote service - status code {}", clientResponse.statusCode());
            return Mono.error(new QuoteNotFoundException("Quote not found"));
        };
    }
}
