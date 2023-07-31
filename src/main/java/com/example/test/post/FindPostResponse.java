package com.example.test.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindPostResponse {

    private Long id;

    private String content;

    private List<PostImageDTO> images;

}
