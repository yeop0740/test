package com.example.test.post;

import com.example.test.image.PostImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String content;

    private List<PostImageDTO> images;

    public PostDTO(Post post) {

        this.id = post.getId();
        this.content = post.getContent();
        List<PostImage> images = post.getImages();
        List<PostImageDTO> imageDTOS = new ArrayList<>();

        for (PostImage image : images) {

            imageDTOS.add(new PostImageDTO(image.getId(), image.getKey()));

        }

        this.images = imageDTOS;

    }

}
