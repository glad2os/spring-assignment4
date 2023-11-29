package com.example.demo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T, ID> {
    Mono<T> save(T entity);
    Flux<T> findAll();
    Mono<T> findById(ID id);
    Mono<T> update(ID id, T entity);
    Mono<Void> deleteById(ID id);
}
