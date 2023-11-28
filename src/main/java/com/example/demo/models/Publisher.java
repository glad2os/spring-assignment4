package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Publisher {
    @Id
    private Long pubId;
    private String name;
    private String address;
}
