package com.example.learnrestlesson1.controller.api;

import com.example.learnrestlesson1.dto.CarDTO;
import com.example.learnrestlesson1.dto.PersonDTO;
import com.example.learnrestlesson1.dto.UpdatePersonDTO;
import com.example.learnrestlesson1.model.Car;
import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.service.PersonService;
import com.example.learnrestlesson1.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class RestPersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;
    private final CarValidator carValidator;

    public RestPersonController(PersonService personService,
                                ModelMapper modelMapper,
                                PersonValidator personValidator, CarValidator carValidator) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
        this.carValidator = carValidator;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPerson()
                .stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{person-id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("person-id") Long personId) {
        return new ResponseEntity<>(convertToPersonDTO(personService.getPersonById(personId)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> savePerson(@RequestBody @Valid PersonDTO personDTO,
                                                 BindingResult bindingResult) {

        Person personToAll = convertToPerson(personDTO);
        personValidator.validate(personToAll, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        personService.savePerson(personToAll);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{person-id}")
    public ResponseEntity<HttpStatus> updatePerson(@RequestBody @Valid UpdatePersonDTO updatePersonDTO,
                                                   BindingResult bindingResult,
                                                   @PathVariable("person-id") Long personId) {

        Person personToAll = convertToUpdatePerson(updatePersonDTO);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        personService.updatePerson(personId, personToAll);
        return ResponseEntity.ok(HttpStatus.OK);

    }


    @PostMapping("/{person-id}/new_car_to_person")
    public ResponseEntity<HttpStatus> newCarToPerson(@RequestBody @Valid CarDTO carDTO,
                                                     BindingResult bindingResult,
                                                     @PathVariable("person-id") Long personId) {

        Car carToAll = convertToCar(carDTO);
        carValidator.validate(carToAll, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        personService.saveCarToPerson(personId, carToAll);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{person-id}/get_in_person_cars")
    public ResponseEntity<List<CarDTO>> getInPersonCars(@PathVariable("person-id") Long personId) {
        return new ResponseEntity<>(personService.getInPersonCars(personId)
                .stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{person-id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("person-id") Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Человек с таким идентификатором не найден!"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private Person convertToUpdatePerson(UpdatePersonDTO updatePersonDTO) {
        return modelMapper.map(updatePersonDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);

    }

    private Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

}
