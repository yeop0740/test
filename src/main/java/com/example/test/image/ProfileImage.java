package com.example.test.image;

import com.example.test.auth.User;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFILE")
public class ProfileImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User user;

}
