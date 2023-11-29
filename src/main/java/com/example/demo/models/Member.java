package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Member {
    @Id
    private Long membId;
    private String name;
    private String address;
    private String membType;
    private Date membDate;
    private Date expiryDate;
}
