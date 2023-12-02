package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.service.BookService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends GenericController<Book, ObjectId> {
    public BookController(BookService service) {
        super(service);
    }
}