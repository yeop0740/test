package com.example.test.schedule;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("APPOINTMENT")
public class Appointment extends Schedule{

    @Enumerated(value = EnumType.STRING)
    private AppointmentType type;

}