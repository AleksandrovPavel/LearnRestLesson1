package com.example.learnrestlesson1.service;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.repositories.CarRepository;
import com.example.learnrestlesson1.util.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Long carId) {
        Optional<Car> foundCar = carRepository.findById(carId);
        return foundCar.orElseThrow(CarNotFoundException::new);
    }

    @Override
    @Transactional
    public void updateCar(Long carId, Car car) {
        Car carUpdate = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        carUpdate.setModel(car.getModel());
        carRepository.save(carUpdate);
    }

    @Override
    @Transactional
    public void deleteCar(Long carId) {
        Car carDelete = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        carRepository.delete(carDelete);
    }


    @Override
    @Transactional(readOnly = true)
    public Person getInCarPerson(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        return car.getPerson();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findBySeries(String series) {
        return carRepository.findBySeries(series);
    }
}
