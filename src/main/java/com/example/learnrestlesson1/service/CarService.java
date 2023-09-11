package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long carId);

    void updateCar(Long carId, Car car);

    void deleteCar(Long carId);

    Long idPerson(Long carId);

    Person getInCarPerson(Long carId);

    Optional<Car> findBySeries(String series);
}
