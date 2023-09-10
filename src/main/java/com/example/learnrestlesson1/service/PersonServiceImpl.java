package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.repositories.PersonRepository;
import com.example.learnrestlesson1.util.PersonNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(Long personId) {
        Optional<Person> foundPerson = personRepository.findById(personId);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);

    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }


}
