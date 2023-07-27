package com.example.test.post;

import com.example.test.comment.Comment;
import com.example.test.common.BaseEntity;
import com.example.test.image.Image;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String content;

    private int likes;

    private int views;

    @OneToMany
    @JoinTable(name = "POST_IMG",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "img_id")
    )
    private List<Image> images = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "POST_COMMENT",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments = new ArrayList<>();

}