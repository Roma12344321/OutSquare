package com.outsquare.spring.services;

import com.outsquare.spring.models.Person;
import com.outsquare.spring.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PersonRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setRole("ROLE_USER");
        person.setPassword(encodedPassword);
        System.out.println(person);
        peopleRepository.save(person);
    }
}
