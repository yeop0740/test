package com.example.test.schedule;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("WALK")
public class Walk extends Schedule{

    private String goal;

    private int achieveRate;

}