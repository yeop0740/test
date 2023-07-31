package com.example.test.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class CreatePostRequest {

    @NotEmpty
    private String content;

    private List<MultipartFile> images;

}
