package com.example.demo.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Member {
    @Id
    private ObjectId id;
    private String name;
    private String address;
    private String membType;
    private Date membDate;
    private Date expiryDate;

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
