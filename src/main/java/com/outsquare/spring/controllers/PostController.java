package com.outsquare.spring.controllers;

import com.outsquare.spring.dto.PersonDto;
import com.outsquare.spring.dto.PostDto;
import com.outsquare.spring.models.Post;
import com.outsquare.spring.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<PostDto> getAllPosts() {
        return postService.getAllPost().stream().map(this::mapPostToPostDto).collect(Collectors.toList());
    }
    @PostMapping()
    public Map<String,String> createPost(@RequestBody PostDto postDto) {
        postService.createPost(mapPostDtoToPost(postDto));
        return Map.of("success","success");
    }

    private PostDto mapPostToPostDto(Post post) {
        return modelMapper.map(post,PostDto.class);
    }
    private Post mapPostDtoToPost(PostDto postDto) {
        return modelMapper.map(postDto,Post.class);
    }
}
