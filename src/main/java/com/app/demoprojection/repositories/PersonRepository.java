package com.app.demoprojection.repositories;

import com.app.demoprojection.dtos.PersonResponseDto;
import com.app.demoprojection.entities.Person;
import com.app.demoprojection.projections.PersonProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> , JpaSpecificationExecutor<Person> {

//    @Query("SELECT new com.app.demoprojection.dtos.PersonResponseDto(p) FROM Person p")
//    List<PersonResponseDto> findAllPersons();

    List<PersonProjection> findAllBy();

    <T> List<T> findAllBy(Class<T> type);

}
