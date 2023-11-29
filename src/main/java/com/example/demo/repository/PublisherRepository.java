package com.example.demo.repository;


import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PublisherRepository extends ReactiveMongoRepository<Publisher, Long> {

}
