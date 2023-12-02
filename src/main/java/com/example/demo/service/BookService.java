package com.example.demo.service;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService implements GenericService<Book, ObjectId> {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> findById(ObjectId id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<Book> update(ObjectId id, Book book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setAvailable(book.getAvailable());
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Mono<Void> deleteById(ObjectId id) {
        return bookRepository.deleteById(id);
    }
}
