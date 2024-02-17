package com.outsquare.spring.services;

import com.outsquare.spring.models.Person;
import com.outsquare.spring.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getAllPeron() {
        return personRepository.findAll();
    }
}
