package com.example.learnrestlesson1.util;

import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.findByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "Адрес электронной почты уже существует");
        }
    }
}
