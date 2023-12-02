package com.example.demo.service;

import com.example.demo.models.Publisher;
import com.example.demo.repository.PublisherRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PublisherService implements GenericService<Publisher, ObjectId> {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Mono<Publisher> save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Flux<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Mono<Publisher> findById(ObjectId id) {
        return publisherRepository.findById(id);
    }

    @Override
    public Mono<Publisher> update(ObjectId id, Publisher publisher) {
        return publisherRepository
                .findById(id)
                .flatMap(existingMember -> {
                    existingMember.setName(existingMember.getName());
                    existingMember.setAddress(existingMember.getAddress());

                    return publisherRepository.save(existingMember);
                });
    }

    @Override
    public Mono<Void> deleteById(ObjectId id) {
        return publisherRepository.deleteById(id);
    }
}
