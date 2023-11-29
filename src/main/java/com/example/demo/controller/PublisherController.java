package com.example.demo.controller;

import com.example.demo.models.Member;
import com.example.demo.models.Publisher;
import com.example.demo.service.MemberService;
import com.example.demo.service.PublisherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController extends GenericController<Publisher, Long> {
    public PublisherController(PublisherService service) {
        super(service);
    }
}