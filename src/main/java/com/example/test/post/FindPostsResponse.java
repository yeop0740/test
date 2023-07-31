package com.example.test.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindPostsResponse<T> {

    private T data;

}
