package com.example.kabisa_backend.model.quoteapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteResponse {
    private String id;
    private String author;
    private String quote;
    private String permalink;
    private String likes;
}
