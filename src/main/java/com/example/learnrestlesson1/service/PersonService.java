package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface PersonService extends UserDetailsService {
    List<Person> getAllPerson();

    Person getPersonById(Long personId);

    void savePerson(Person person);

    Optional<Person> findByUsername(String email);

    void updatePerson(Long personId, Person person);

    void deletePerson(Long personId);

    List<Car> getInPersonCars(Long personId);

    void saveCarToPerson(Long personId, Car car);

    Optional<Person> findUserByUsername(String username);

}
