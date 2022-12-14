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
    private Integer id;
    private String author;
    private String quote;
    private String permalink;
    private Integer likes = 0;
}
