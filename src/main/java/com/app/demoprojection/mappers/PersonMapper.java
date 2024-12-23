package com.app.demoprojection.mappers;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "isMajeur" , expression = "java(person.getAge() >= 18 ? true : false)")
    PersonResponseDto toDto(Person person);
    List<PersonResponseDto> toDtoList(List<Person> persons);
    Person toEntity(PersonRequestDto personRequestDto);

}
