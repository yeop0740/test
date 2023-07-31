package com.example.test.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class UserCommentResponse {

    @NotEmpty
    private String nickname;

    private List<CommentsDTO> comments;


}
