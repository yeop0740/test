package com.example.test.image;

import com.example.test.post.Post;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("POST")
public class PostImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
