package com.example.test.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCommentDTO {

    @NotNull
    private Long comment_id;

    @NotEmpty
    private String nickname;

    private Long parentId;

    @NotEmpty
    private String content;

}
