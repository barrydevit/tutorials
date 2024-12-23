package com.app.demoprojection.specs;

import com.app.demoprojection.dtos.PersonSearchDto;
import com.app.demoprojection.entities.Person;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonSpecification {

    public static Specification<Person> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like( criteriaBuilder.lower( root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Person> getByAge(int age) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo( root.get("age"), age);
    }

    public static Specification<Person> getPersonSpec(PersonSearchDto personSearchDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(personSearchDto.firstName())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower( root.get("firstName")), "%" + personSearchDto.firstName().toLowerCase() + "%"));
            }

            if (Objects.nonNull(personSearchDto.lastName())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower( root.get("lastName")), "%" + personSearchDto.lastName().toLowerCase() + "%"));
            }

            if (Objects.nonNull(personSearchDto.addresse())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower( root.get("addresse").get("ville")), "%" + personSearchDto.addresse().toLowerCase() + "%"));
            }

            if (Objects.nonNull(personSearchDto.age())){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), personSearchDto.age()));
            }

            if (Objects.nonNull(personSearchDto.profession())){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower( root.get("profession")), "%" + personSearchDto.profession().toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
