package com.example.kabisa_backend.exception;

import lombok.Getter;

@Getter
public class QuoteNotFoundException extends RuntimeException {

    public QuoteNotFoundException(String error) {
        super(error);
    }

}
