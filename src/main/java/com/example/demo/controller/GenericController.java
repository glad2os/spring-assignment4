package com.example.demo.controller;

import com.example.demo.service.GenericService;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericController <T, ID>{
    private final GenericService<T, ID> service;

    public GenericController(GenericService<T, ID> service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<T>> create(@RequestBody T entity) {
        return service.save(entity)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<T>> findById(@PathVariable ID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<T>> update(@PathVariable ID id, @RequestBody T entity) {
        return service.update(id, entity)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable ID id) {
        return service.deleteById(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
