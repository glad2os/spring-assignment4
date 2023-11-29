package com.example.demo.repository;


import com.example.demo.models.Book;
import com.example.demo.models.Member;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MemberRepository extends ReactiveMongoRepository<Member, Long> {

}
