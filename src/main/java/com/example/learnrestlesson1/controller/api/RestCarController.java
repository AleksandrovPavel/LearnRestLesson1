package com.example.learnrestlesson1.controller.api;

import com.example.learnrestlesson1.dto.CarDTO;
import com.example.learnrestlesson1.dto.PersonDTO;
import com.example.learnrestlesson1.dto.UpdateCarDTO;
import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.service.CarService;
import com.example.learnrestlesson1.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
public class RestCarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestCarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars()
                .stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{car-id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("car-id") Long carId) {
        return new ResponseEntity<>(convertToCarDTO(carService.getCarById(carId)),
                HttpStatus.OK);
    }

    @PatchMapping ("/{car-id}")
    public ResponseEntity<HttpStatus> updateCar(@RequestBody @Valid UpdateCarDTO updateCarDTO,
                                                BindingResult bindingResult,
                                                @PathVariable("car-id") Long carId) {

        Car carToAll = convertToUpdateCar(updateCarDTO);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        carService.updateCar(carId, carToAll);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{car-id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("car-id") Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{car-id}/get_in_car_person")
    public ResponseEntity<PersonDTO> getInCarPerson(@PathVariable("car-id") Long carId) {
        return new ResponseEntity<>(convertToPersonDTO(carService.getInCarPerson(carId)),
                HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<CarErrorResponse> handleException(CarNotFoundException e) {
        CarErrorResponse response = new CarErrorResponse(
                "Автомобиль с таким идентификатором не найден!"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    private Car convertToUpdateCar(UpdateCarDTO updateCarDTO) {
        return modelMapper.map(updateCarDTO, Car.class);
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);

    }
}
