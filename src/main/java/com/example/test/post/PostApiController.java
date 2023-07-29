package com.example.test.post;

import com.example.test.image.PostImage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/post/{uuid}")
    public CreatePostResponse createPost(@PathVariable UUID uuid, @ModelAttribute @Valid CreatePostRequest request) throws IOException {

        Long id = postService.createPost(uuid, request.getContent(), request.getImages());
        return new CreatePostResponse(id);

    }

    @GetMapping("/api/v1/post/{postId}")
    public FindPostResponse findPost(@PathVariable Long postId) {

        Post post = postService.findPost(postId);
        return new FindPostResponse(post.getId(), post.getContent());

    }

    @GetMapping("/api/v1/posts")
    public FindPostsResponse findPosts() {

        return new FindPostsResponse(postService.findPosts());

    }

}
