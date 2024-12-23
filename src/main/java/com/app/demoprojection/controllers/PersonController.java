package com.app.demoprojection.controllers;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.dtos.PersonSearchDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.projections.PersonProjection;
import com.app.demoprojection.projections.PersonProjection2;
import com.app.demoprojection.services.impl.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(@RequestBody PersonRequestDto personRequestDto) {
        return new ResponseEntity<>(personService.save(personRequestDto) , HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonResponseDto> updatePerson( @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.update(personRequestDto));
    }

    @GetMapping("/p")
    public ResponseEntity<List<PersonProjection>> findAllProjections() {
        return ResponseEntity.ok(personService.findAllPersonProjection());
    }

    @GetMapping("/generic")
    public ResponseEntity<List<PersonProjection>> findAllPersonProjectionGeneric() {
        return ResponseEntity.ok(personService.findAllPersonProjectionWithGeneric(PersonProjection.class));
    }

    @GetMapping("/generic2")
    public ResponseEntity<List<PersonProjection2>> findAllPersonProjectionGeneric2() {
        return ResponseEntity.ok(personService.findAllPersonProjectionWithGeneric(PersonProjection2.class));
    }

    @GetMapping("/bycriteria")
    public ResponseEntity<List<PersonResponseDto>> findAllByCriteria(@RequestParam(required = false) String firstName ,@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(personService.findAllPersonByCriteria(firstName , age));
    }

    @PostMapping("/bycriteria")
    public ResponseEntity<List<PersonResponseDto>> findAllPersonByCriteria(@RequestBody PersonSearchDto personSearchDto){
        return ResponseEntity.ok(personService.findAllPersonByCriteriaDto(personSearchDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }


}
