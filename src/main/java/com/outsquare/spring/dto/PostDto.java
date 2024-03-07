package com.outsquare.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {
    private int id;
    private String text;
    private Long likeCount;
    private List<PostImageDto> postImages;
    private PersonDto person;
}
