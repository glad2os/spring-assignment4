package com.example.demo.repository;


import com.example.demo.models.Publisher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PublisherRepository extends ReactiveMongoRepository<Publisher, ObjectId> {

}
