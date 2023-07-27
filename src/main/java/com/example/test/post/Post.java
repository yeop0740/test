package com.example.test.post;

import com.example.test.auth.User;
import com.example.test.comment.Comment;
import com.example.test.common.BaseEntity;
import com.example.test.image.Image;
import com.example.test.image.PostImage;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String content;

    private int likes;

    private int views;

    @OneToMany(mappedBy = "post")
    private List<PostImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User writer;

    public void setWriter(User user) {

        this.writer = user;
        writer.getPosts().add(this);

    }

    public static Post createPost(User user, String content) {

        Post post = new Post();
        post.content = content;
        post.setWriter(user);
        return post;

    }

}