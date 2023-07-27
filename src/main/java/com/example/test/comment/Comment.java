package com.example.test.comment;

import com.example.test.common.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @OneToMany
    @JoinTable(name = "PARENT_CHILDREN",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Comment> children;

}