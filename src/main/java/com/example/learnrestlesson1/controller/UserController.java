package com.example.learnrestlesson1.controller;

import com.example.learnrestlesson1.model.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String getCurrentUser(@AuthenticationPrincipal Person user, Model model) {
        model.addAttribute("personId", user.getId());
        return "/user/user";
    }
}
