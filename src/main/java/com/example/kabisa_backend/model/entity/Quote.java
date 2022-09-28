package com.example.kabisa_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Quote {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String author;

    @Column
    private String quote;

    @Column
    private String permalink;

    @Column
    private Integer likes;
}
