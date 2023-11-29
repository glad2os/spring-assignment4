package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends GenericController<Book, Long> {
    public BookController(BookService service) {
        super(service);
    }
}