package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPerson();

    Person getPersonById(Long personId);

    void savePerson(Person person);

    Optional<Person> findByEmail(String email);

    void updatePerson(Long personId);

    void deletePerson()
}
