package com.app.demoprojection.services;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.dtos.PersonSearchDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<PersonProjection> findAllPersonProjection();

    <T> List<T> findAllPersonProjectionWithGeneric(Class<T> type);

    List<PersonResponseDto> findAllPersonByCriteria(String firstName , Integer age);
    List<PersonResponseDto> findAllPersonByCriteriaDto(PersonSearchDto personSearchDto);

    PersonResponseDto save(PersonRequestDto personRequestDto);
    PersonResponseDto update(PersonRequestDto personRequestDto);

    Person findById(Long id);


}
