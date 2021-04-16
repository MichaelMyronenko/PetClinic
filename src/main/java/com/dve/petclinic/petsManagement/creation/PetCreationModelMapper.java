package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.pet.Pet;

public interface PetCreationModelMapper <T extends Pet, S extends PetCreationModel>{
    T mapToEntity(S model);
}
