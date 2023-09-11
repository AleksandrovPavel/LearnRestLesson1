package com.example.learnrestlesson1.repositories;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByPerson(Person person);

    Optional<Car> findBySeries(String series);
}
