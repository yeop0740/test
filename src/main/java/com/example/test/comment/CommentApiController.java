package com.example.test.comment;

import com.example.test.auth.User;
import com.example.test.post.Post;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/api/v1/user/comments/{uuid}")
    public UserCommentResponse findUserComments(@PathVariable UUID uuid) {

        User findUser = commentService.findCommentsWriter(uuid);
        List<Comment> findComments = findUser.getComments();
        List<CommentsDTO> dtos = new ArrayList<>();

        for (Comment comment : findComments) {

            Comment parent = comment.getParent();

            if (parent != null) {

                dtos.add(new CommentsDTO(comment.getId(), comment.getPost().getId(), parent.getId(), comment.getContent()));

            } else {

                dtos.add(new CommentsDTO(comment.getId(), comment.getPost().getId(), null, comment.getContent()));

            }
        }

        return new UserCommentResponse(findUser.getNickname(), dtos);

    }

    @GetMapping("api/v1/post/comments/{postId}")
    public PostCommentResponse findPostComments(@PathVariable Long postId) {

        Post findPost = commentService.findCommentsPost(postId);
        List<Comment> comments = findPost.getComments();
        List<PostCommentDTO> dtos = new ArrayList<>();

        for (Comment comment : comments) {

            Comment parent = comment.getParent();

            if (parent != null) {

                dtos.add(new PostCommentDTO(comment.getId(), comment.getWriter().getNickname(), comment.getParent().getId(), comment.getContent()));

            } else {

                dtos.add(new PostCommentDTO(comment.getId(), comment.getWriter().getNickname(), null, comment.getContent()));

            }

        }

        return new PostCommentResponse(findPost.getId(), findPost.getWriter().getNickname(), dtos);

    }

    @PutMapping("/api/v1/comment/{commentId}")
    public UpdateCommentResponse updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {

        Long id = commentService.updateComment(commentId, request.getContent());
        return new UpdateCommentResponse(id);

    }

    @DeleteMapping("/api/v1/comment/{commentId}")
    public DeleteCommentResponse deleteComment(@PathVariable Long commentId) {

        Long postId = commentService.deleteComment(commentId);
        return new DeleteCommentResponse(postId);

    }

}
