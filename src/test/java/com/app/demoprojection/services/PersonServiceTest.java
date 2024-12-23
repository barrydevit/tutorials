package com.app.demoprojection.services;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.errors.ResourceNotFoundException;
import com.app.demoprojection.mappers.PersonMapper;
import com.app.demoprojection.repositories.PersonRepository;
import com.app.demoprojection.services.impl.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
  @InjectMocks
    private PersonService personService;

  @Mock
    private PersonRepository personRepository;

  @Mock
  private PersonMapper personMapper;

  @Mock
  private IAddresseService addresseService;

  @BeforeEach
  void setUp() {
      personRepository.save(new Person("John" , "Smith" , 1L));
  }

  @Test
    void findPersonById() {

      Person person = new Person();
      person.setId(1L);
      person.setFirstName("John");
      person.setLastName("Smith");
      when(personRepository.findById(1L)).thenReturn(Optional.of(person));

      Person personFound = personService.findById(1L);

      Assertions.assertEquals(person.getFirstName(), personFound.getFirstName());
      Assertions.assertEquals(person.getLastName(), personFound.getLastName());


  }

  @Test
    void notFindPersonById() {

      ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
          personService.findById(2L);
      });
      Assertions.assertEquals("Person with id = 2 not found", exception.getMessage());
  }

  @Test
    void savePerson() {
      Person person = new Person();
      person.setId(1L);
      person.setFirstName("John");
      person.setLastName("Smith");
      when(personRepository.save(person)).thenReturn(person);
      PersonRequestDto personRequestDto = new PersonRequestDto(1L , "John" , "Smith" , "" ,1L , "" ,"", 20 );
      PersonResponseDto personResponseDto = new PersonResponseDto(1L , "John" , "Smith" ,"",20 , true , null );
      when(personMapper.toEntity(personRequestDto)).thenReturn(person);
      when(personMapper.toDto(person)).thenReturn(personResponseDto);

      PersonResponseDto actualReponse = personService.save(personRequestDto);

      Assertions.assertEquals(personResponseDto.firstName() , actualReponse.firstName());
      Assertions.assertEquals(personResponseDto.lastName() , actualReponse.lastName());
      Assertions.assertEquals(personResponseDto.id() , actualReponse.id());

  }
}
