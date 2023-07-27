package com.example.test.location;

import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

@Embeddable
@RequiredArgsConstructor
public class Coordinate {

    private Double latitude;

    private Double longitude;

}
