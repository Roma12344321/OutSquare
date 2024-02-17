package com.outsquare.spring.controllers;

import com.outsquare.spring.dto.AuthenticationDto;
import com.outsquare.spring.dto.PersonDto;
import com.outsquare.spring.mapper.PersonMapper;
import com.outsquare.spring.models.Person;
import com.outsquare.spring.security.JWTUtil;
import com.outsquare.spring.services.RegistrationService;
import com.outsquare.spring.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final PersonMapper personMapper;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDto personDto, BindingResult bindingResult) {
        Person person = personMapper.mapPersonDtoToPerson(personDto);
        System.out.println(person);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message", "error");
        }
        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt_token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                authenticationDto.getUsername(), authenticationDto.getPassword()
        );
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "incorrect credentials");
        }
        String token = jwtUtil.generateToken(authenticationDto.getUsername());
        return Map.of("jwt_token", token);
    }
}