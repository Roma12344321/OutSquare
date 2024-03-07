package com.outsquare.spring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_image")
@NoArgsConstructor
@Data
public class PostImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "path")
    private String path;
    @ManyToOne
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private Post post;
}
