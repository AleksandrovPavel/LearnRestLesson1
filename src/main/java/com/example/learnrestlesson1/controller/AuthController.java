package com.example.learnrestlesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/login")
public class AuthController {

    @GetMapping()
    public String login() {
        return "person/login";
    }
}