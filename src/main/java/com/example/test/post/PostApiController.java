package com.example.test.post;

import com.example.test.image.PostImage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        List<PostImageDTO> imageDTOs = new ArrayList<>();
        Post post = postService.findPost(postId);
        List<PostImage> images = post.getImages();

        for (PostImage image : images) {

            imageDTOs.add(new PostImageDTO(image.getId(), image.getKey()));

        }

        return new FindPostResponse(post.getId(), post.getContent(), imageDTOs);

    }

    @GetMapping("/api/v1/posts")
    public FindPostsResponse<List<PostDTO>> findPosts() {

        List<PostDTO> dtos = new ArrayList<>();
        List<Post> posts = postService.findPosts();

        for (Post post : posts) {

            dtos.add(new PostDTO(post));

        }

        return new FindPostsResponse<>(dtos);

    }

    @PutMapping("/api/v1/post/{postId}")
    public CreatePostResponse updatePost(@PathVariable Long postId, @ModelAttribute @Valid CreatePostRequest request) throws IOException {

        Long id = postService.update(postId, request.getContent(), request.getImages());
        return new CreatePostResponse(id);

    }

    @DeleteMapping("/api/v1/post/{postId}")
    public DeletePostResponse deletePost(@PathVariable Long postId) {

        Long id = postService.delete(postId);
        return new DeletePostResponse(id);

    }

}
