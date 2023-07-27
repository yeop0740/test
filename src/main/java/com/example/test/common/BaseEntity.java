package com.example.test.common;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    private LocalDateTime created_time;

    private LocalDateTime modified_time;

}
