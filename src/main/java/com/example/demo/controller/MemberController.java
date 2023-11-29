package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.models.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController extends GenericController<Member, Long> {
    public MemberController(MemberService service) {
        super(service);
    }
}