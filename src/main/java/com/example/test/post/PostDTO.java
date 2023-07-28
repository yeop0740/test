package com.example.test.post;

import com.example.test.image.PostImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String content;

    private List<PostImage> images;

    public PostDTO(Post post) {

        this.id = post.getId();
        this.content = post.getContent();
        this.images = post.getImages();

    }

}
