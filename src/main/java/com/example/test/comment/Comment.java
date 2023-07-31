package com.example.test.comment;

import com.example.test.auth.User;
import com.example.test.common.BaseEntity;
import com.example.test.post.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "comment_id")
    private Comment parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private void setContent(String content) {

        this.content = content;

    }

    private void setPost(Post post) {

        this.post = post;
        post.getComments().add(this);

    }

    private void setWriter(User writer) {

        this.writer = writer;
        writer.getComments().add(this);

    }

    private void setParent(Comment parent) {

        this.parent = parent;
        parent.children.add(this);

    }

    public static Comment createComment(Post post, Comment parent, User writer, String content) {

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setWriter(writer);
        comment.setContent(content);

        if (parent != null) {

            comment.setParent(parent);

        }

        return comment;

    }

    public void change(String content) {

        this.content = content;

    }

}