package com.example.test.auth;

import com.example.test.comment.Comment;
import com.example.test.common.BaseEntity;
import com.example.test.location.Location;
import com.example.test.pet.Pet;
import com.example.test.post.Post;
import jakarta.persistence.*;

import java.util.List;


@Entity(name = "USERS")
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String uuid;

    private String nickname;

    private String email;

    private String password;

    private int likes;

    private String provider;

    private String providerId;

    @OneToMany
    @JoinTable(name = "USER_PET",
            joinColumns = @JoinColumn(name = "uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;

    @OneToMany
    @JoinTable(name = "USER_POST",
            joinColumns = @JoinColumn(name = "uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts;

    @OneToMany
    @JoinTable(name = "USER_COMMENT",
            joinColumns = @JoinColumn(name = "uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments;

    @OneToMany
    @JoinTable(name = "USER_LOCATION",
            joinColumns = @JoinColumn(name = "uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

}
