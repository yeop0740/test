package com.example.test.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindPostResponse {

    private Long id;

    private String content;

}
