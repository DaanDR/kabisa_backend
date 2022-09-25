package com.example.kabisa_backend.repository;

import com.example.kabisa_backend.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    Quote findByQuote(String quote);
    List<Quote> findAllByOrderByLikesDesc();
    List<Quote> findByQuoteContainingIgnoreCase(String word);
}
