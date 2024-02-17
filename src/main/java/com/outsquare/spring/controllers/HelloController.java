package com.outsquare.spring.controllers;

import com.outsquare.spring.dto.PersonDto;
import com.outsquare.spring.mapper.PersonMapper;
import com.outsquare.spring.models.Person;
import com.outsquare.spring.security.PersonDetails;
import com.outsquare.spring.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class HelloController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/show")
    public PersonDto show() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return mapPersonToPersonDto(personDetails.getPerson());
    }

    @GetMapping("/people/all")
    public List<PersonDto> getAllPerson() {
        return personService.getAllPeron().stream().map(this::mapPersonToPersonDto).collect(Collectors.toList());
    }

    private PersonDto mapPersonToPersonDto(Person person) {
        return personMapper.mapPersonToPersonDto(person);
    }
}
