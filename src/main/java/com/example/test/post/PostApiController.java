package com.example.test.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("api/v1/post/{uuid}")
    public CreatePostResponse createPost(@PathVariable UUID uuid, @RequestBody @Valid CreatePostRequest request) {

        Long id = postService.createPost(uuid, request.getContent());
        return new CreatePostResponse(id);

    }
}
