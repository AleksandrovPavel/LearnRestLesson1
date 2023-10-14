package com.example.learnrestlesson1.controller.api;

import com.example.learnrestlesson1.dto.PersonDTO;
import com.example.learnrestlesson1.model.Person;
import com.example.learnrestlesson1.service.PersonService;
import com.example.learnrestlesson1.util.ErrorsUtil;
import com.example.learnrestlesson1.util.PersonValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RestRegisterController {

    private final PersonService personService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;

    private final PasswordEncoder passwordEncoder;

    public RestRegisterController(PersonService personService,
                                  ModelMapper modelMapper,
                                  PersonValidator personValidator, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerPerson(@RequestBody @Valid PersonDTO personDTO,
                                                 BindingResult bindingResult) {

        Person personToAll = convertToPerson(personDTO);
        personValidator.validate(personToAll, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        personService.savePerson(personToAll);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        personDTO.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        return modelMapper.map(personDTO, Person.class);
    }
}
