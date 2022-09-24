package com.example.kabisa_backend.client;

import com.example.kabisa_backend.model.quoteapi.QuoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .bodyToMono(QuoteResponse.class);
    }

}
