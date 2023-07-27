package com.example.test.auth;

import com.example.test.comment.Comment;
import com.example.test.common.BaseEntity;
import com.example.test.image.ProfileImage;
import com.example.test.location.Location;
import com.example.test.pet.Pet;
import com.example.test.post.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "USERS")
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    private String nickname;

    @Column(unique = true)
    private String email;

    private String password;

    private int likes;

    private String provider;

    private String providerId;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ProfileImage> images = new ArrayList<>();

}
