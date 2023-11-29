package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private Double price;
    private Boolean available;
}
