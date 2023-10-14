package com.example.learnrestlesson1.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/person")
public class PersonController {

    @GetMapping()
    public String getAllPersons() {
        return "person/all_persons";
    }

    @GetMapping("/new_person")
    public String newPerson() {
        return "/person/new_person";
    }

    @GetMapping("/{person-id}")
    public String getPersonById(@PathVariable("person-id") Long personId
            , Model model) {
        model.addAttribute("personId", personId);
        return "/person/person_by_id";
    }

    @GetMapping("/{person-id}/update_person")
    public String updatePerson(@PathVariable("person-id") Long personId,
                               Model model) {
        model.addAttribute("personId", personId);
        return "/person/update_person";
    }
}
