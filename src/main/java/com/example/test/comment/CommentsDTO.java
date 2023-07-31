package com.example.test.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentsDTO {

    @NotNull
    private Long commentId;

    @NotNull
    private Long postId;

    private Long parentId;

    @NotEmpty
    private String content;

}
