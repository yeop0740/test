package com.example.test.location;

import com.example.test.auth.User;
import com.example.test.common.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Location extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Embedded
    private Coordinate coordinate;

    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User writer;

}
