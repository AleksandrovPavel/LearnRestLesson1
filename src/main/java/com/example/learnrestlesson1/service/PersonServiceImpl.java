package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.repositories.CarRepository;
import com.example.learnrestlesson1.repositories.PersonRepository;
import com.example.learnrestlesson1.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
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
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void updatePerson(Long personId, Person person) {
        Person personUpdate = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        personUpdate.setFirstName(person.getFirstName());
        personUpdate.setLastName(person.getLastName());
        personRepository.save(personUpdate);

    }

    @Override
    @Transactional
    public void deletePerson(Long personId) {
        Person personDelete = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        personRepository.delete(personDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getInPersonCars(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        return carRepository.findAllByPerson(person);
    }

    @Override
    @Transactional
    public void saveCarToPerson(Long personId, Car car) {
        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        car.setPerson(person);
        carRepository.save(car);
    }


}
