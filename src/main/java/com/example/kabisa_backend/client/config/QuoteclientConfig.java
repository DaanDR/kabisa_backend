package com.example.kabisa_backend.client.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@Slf4j
@Data
public class QuoteclientConfig {

    @Value("${quote.url:default}")
    private String quoteUrl;

    @Bean
    public WebClient quoteClientConfig() {
        log.info("Initializing quoteClient for Url: {}", this.quoteUrl);
        return quoteClientBuilder(quoteUrl);
    }

    private WebClient quoteClientBuilder(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
