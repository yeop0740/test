package com.example.test.pet;

import com.example.test.common.BaseEntity;
import com.example.test.image.Image;
import com.example.test.schedule.Schedule;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @OneToMany
    @JoinTable(name = "PET_SCHEDULE",
    joinColumns = @JoinColumn(name = "pet_id"),
    inverseJoinColumns = @JoinColumn(name = "schedule_id"))

    private List<Schedule> schedules = new ArrayList<>();

    private int monthlyWalkAmount;

    @OneToOne(fetch = FetchType.LAZY)
    private Image img;

}