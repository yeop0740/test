package com.example.test.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class CreatePostRequest {

    @NotEmpty
    private String content;

}
