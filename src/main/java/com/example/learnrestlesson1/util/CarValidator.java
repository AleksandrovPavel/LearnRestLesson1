package com.example.learnrestlesson1.util;

import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.service.CarService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class CarValidator implements Validator {

    private final CarService carService;

    public CarValidator(CarService carService) {
        this.carService = carService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Car car = (Car) target;

        if (carService.findBySeries(car.getSeries()).isPresent()) {
            errors.rejectValue("series", "Серийный номер автомобиля уже существует");
        }
    }
}
