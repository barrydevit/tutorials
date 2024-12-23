package com.app.demoprojection.controllers;

import com.app.demoprojection.dtos.PersonRequestDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.repositories.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    private  final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        personRepository.save(new Person("John", "Doe" , 1L));
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void getPersonById() throws Exception {
        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Doe"))
                .andExpect(jsonPath("$.lastName").value("John"));
    }

    @Test
    void notFoundPersonById() throws Exception {
        mockMvc.perform(get("/persons/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void savePerson() throws Exception {
        PersonRequestDto personRequestDto = new PersonRequestDto(1L , "John" , "Smith" , "" ,1L , "" ,"", 20 );
        mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.id").value(1L));
    }
}
