package com.example.learnrestlesson1.controller;

import com.example.learnrestlesson1.dto.PersonDTO;
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

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;

    public PersonController(PersonService personService,
                            ModelMapper modelMapper,
                            PersonValidator personValidator) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public List<PersonDTO> getAllPerson() {
        return personService.getAllPerson()
                .stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") Long id) {
        return convertToPersonDTO(personService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create (@RequestBody @Valid PersonDTO personDTO,
                                              BindingResult bindingResult) {

        Person personToAll = convertToPerson(personDTO);
        personValidator.validate(personToAll, bindingResult);

        if(bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        personService.savePerson(personToAll);
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
        return modelMapper.map(personDTO,Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);

    }

}
