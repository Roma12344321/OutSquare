package com.outsquare.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonDto {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Некорректное имя")
    private String username;

    private String password;

    @Email(message = "Некорректный email")
    @Size(min = 2, max = 120, message = "Некорректный email")
    private String email;

    @Min(value = 1900, message = "Некорректный год")
    private int year;

    private String avatar;

}
