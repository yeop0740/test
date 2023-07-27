package com.example.test.image;

import com.example.test.common.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Image extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    private String imgUrl;

    @Enumerated(value = EnumType.STRING)
    private ImageType type;

}
