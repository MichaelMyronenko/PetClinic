package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.pet.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PetCreationModel {

    @NotBlank
    @Size(min = 2, max = 40)
    String name;

    public Pet toEntity() {
        return new Pet(name);
    }

    public PetCreationModel(String name) {
        this.name = name;
    }

    public PetCreationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
