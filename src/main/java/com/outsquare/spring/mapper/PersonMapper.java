package com.outsquare.spring.mapper;

import com.outsquare.spring.dto.PersonDto;
import com.outsquare.spring.models.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonMapper {

    private final ModelMapper modelMapper;
    public Person mapPersonDtoToPerson(PersonDto personDto) {
        return modelMapper.map(personDto,Person.class);
    }
    public PersonDto mapPersonToPersonDto(Person person) {
        return modelMapper.map(person,PersonDto.class);
    }
}
