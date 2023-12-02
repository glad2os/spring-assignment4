package com.example.demo.repository;


import com.example.demo.models.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MemberRepository extends ReactiveMongoRepository<Member, ObjectId> {

}
