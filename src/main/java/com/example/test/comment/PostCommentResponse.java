package com.example.test.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PostCommentResponse {

    @NotNull
    private Long postId;

    @NotEmpty
    private String nickname;

    private List<PostCommentDTO> comments;

}
