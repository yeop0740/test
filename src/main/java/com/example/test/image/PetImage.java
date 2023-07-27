package com.example.test.image;

import com.example.test.pet.Pet;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PET")
public class PetImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
