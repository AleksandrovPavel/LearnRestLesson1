package com.example.learnrestlesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/register")
public class RegisterController {

    @GetMapping
    public String register() {
        return "person/register";
    }
}
