package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public Car getCarById(Long carId) {
        return null;
    }

    @Override
    public void updateCar(Long carId, Car car) {

    }

    @Override
    public void deleteCar(Long carId) {

    }

    @Override
    public Long idPerson(Long carId) {
        return null;
    }
}
