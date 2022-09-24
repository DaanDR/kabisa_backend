package com.example.kabisa_backend.model;

import lombok.Data;

@Data
public class QuoteResponse {
    private String id;
    private String author;
    private String quote;
    private String permalink;
}
