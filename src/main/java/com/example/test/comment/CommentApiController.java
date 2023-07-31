package com.example.test.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/v1/comment/{uuid}")
    public CreateCommentResponse createComment(@PathVariable UUID uuid, @RequestBody @Valid CreateCommentRequest request) {

        Long id = commentService.createComment(request.getPostId(), request.getParentId(), uuid, request.getContent());
        return new CreateCommentResponse(id);

    }

}
