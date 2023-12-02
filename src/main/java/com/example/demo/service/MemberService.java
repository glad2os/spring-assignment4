package com.example.demo.service;

import com.example.demo.models.Member;
import com.example.demo.repository.MemberRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberService implements GenericService<Member, ObjectId> {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository MemberRepository) {
        this.memberRepository = MemberRepository;
    }

    @Override
    public Mono<Member> save(Member Member) {
        return memberRepository.save(Member);
    }

    @Override
    public Flux<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Mono<Member> findById(ObjectId id) {
        return memberRepository.findById(id);
    }

    @Override
    public Mono<Member> update(ObjectId id, Member Member) {
        return memberRepository
                .findById(id)
                .flatMap(existingMember -> {
                    existingMember.setAddress(Member.getAddress());
                    existingMember.setName(Member.getName());
                    existingMember.setMembDate(Member.getMembDate());
                    existingMember.setExpiryDate(Member.getExpiryDate());
                    existingMember.setMembType(Member.getMembType());

                    return memberRepository.save(existingMember);
                });
    }

    @Override
    public Mono<Void> deleteById(ObjectId id) {
        return memberRepository.deleteById(id);
    }
}
