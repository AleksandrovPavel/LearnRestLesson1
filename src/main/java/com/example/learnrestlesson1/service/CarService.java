package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long carId);

    void updateCar(Long carId, Car car);

    void deleteCar(Long carId);

    Long idPerson(Long carId);
}
