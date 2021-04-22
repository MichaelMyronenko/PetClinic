package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PetUpdateModel {

    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    public void updateEntity(Pet entity) {
        entity.setName(name);
    }

    public PetUpdateModel(String name) {
        this.name = name;
    }

    public PetUpdateModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
