package com.example.test.pet;

import com.example.test.auth.User;
import com.example.test.common.BaseEntity;
import com.example.test.image.Image;
import com.example.test.image.PetImage;
import com.example.test.schedule.Schedule;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @OneToMany(mappedBy = "pet")
    private List<Schedule> schedules = new ArrayList<>();

    private int monthlyWalkAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User owner;

    @OneToMany(mappedBy = "pet")
    private List<PetImage> images = new ArrayList<>();

}