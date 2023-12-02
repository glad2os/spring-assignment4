package com.example.demo.controller;

import com.example.demo.models.Member;
import com.example.demo.service.MemberService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController extends GenericController<Member, ObjectId> {
    public MemberController(MemberService service) {
        super(service);
    }
}