package com.app.demoprojection.services.impl;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.dtos.PersonSearchDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.errors.DocumentNotValidException;
import com.app.demoprojection.errors.ResourceNotFoundException;
import com.app.demoprojection.mappers.PersonMapper;
import com.app.demoprojection.projections.PersonProjection;
import com.app.demoprojection.repositories.PersonRepository;
import com.app.demoprojection.services.IAddresseService;
import com.app.demoprojection.services.IPersonService;
import com.app.demoprojection.specs.PersonSpecification;
import com.app.demoprojection.validations.groups.Update;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final IAddresseService addresseService;
    private final Validator validator;


    public PersonService(PersonRepository personRepository, PersonMapper personMapper, IAddresseService addresseService, Validator validator) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.addresseService = addresseService;

        this.validator = validator;
    }



    @Override
    public List<PersonProjection> findAllPersonProjection() {
        return personRepository.findAllBy();
    }

    @Override
    public <T> List<T> findAllPersonProjectionWithGeneric(Class<T> type) {
        return personRepository.findAllBy(type);
    }

    @Override
    public List<PersonResponseDto> findAllPersonByCriteria(String firstName, Integer age) {
        Specification<Person> personSpecification = Specification.where(null);

        if(Objects.nonNull(firstName)) {
            personSpecification = personSpecification.and(PersonSpecification.byName(firstName));
        }
        if(Objects.nonNull(age)) {
            personSpecification = personSpecification.and(PersonSpecification.getByAge(age));
        }
        return personMapper.toDtoList(personRepository.findAll(personSpecification));
    }

    @Override
    public List<PersonResponseDto> findAllPersonByCriteriaDto(PersonSearchDto personSearchDto) {
        return personMapper.toDtoList(personRepository.findAll(PersonSpecification.getPersonSpec(personSearchDto)));
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(String.format("Person with id = %d not found", id))
        );
    }

    @Override
    public PersonResponseDto save(PersonRequestDto personRequestDto) {
        Person person = personMapper.toEntity(personRequestDto);
        person.setAddresse(addresseService.getAddressById(personRequestDto.addresseId()).orElse(null));
        Person savedPerson = personRepository.save(person);
        return personMapper.toDto(savedPerson);
    }

    @Override
    public PersonResponseDto update(PersonRequestDto personRequestDto) {
        Set<ConstraintViolation<PersonRequestDto>> violations = validator.validate(personRequestDto , Update.class);

        if (!violations.isEmpty()){
            throw new DocumentNotValidException(violations);
        }
        Person person = personRepository.findById(personRequestDto.id()).orElseThrow(
                () ->new ResourceNotFoundException(String.format("Person with id %d not found", personRequestDto.id()))
        );
        person.setFirstName(personRequestDto.firstName());
        person.setLastName(personRequestDto.lastName());
        person.setAge(personRequestDto.age());
        person.setAddresse(addresseService.getAddressById(personRequestDto.addresseId()).orElse(null));
        person.setEmail(personRequestDto.email());
        person.setPhone(personRequestDto.phone());
        person.setProfession(personRequestDto.profession());
        Person savedPerson = personRepository.save(person);
        return personMapper.toDto(savedPerson);
    }




}
