package com.outsquare.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "person")
public class Person {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Некорректное имя")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Size(min = 2, max = 100, message = "Некорректный пароль")
    private String password;

    @Column(name = "email")
    @Email(message = "Некорректный email")
    @Size(min = 2, max = 120, message = "Некорректный email")
    private String email;

    @Min(value = 1900, message = "Некорректный год")
    @Column(name = "year")
    private int year;

    @Column(name = "role")
    private String role;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "person")
    private List<Post> posts;

    @OneToMany(mappedBy = "person")
    private List<Comment> comments;

    @OneToMany(mappedBy = "person")
    private List<PostLike> postLikes;
}