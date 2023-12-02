package com.example.demo.repository;


import com.example.demo.models.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, ObjectId> {

}
