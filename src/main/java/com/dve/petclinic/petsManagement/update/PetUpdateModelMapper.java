package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;

public interface PetUpdateModelMapper<T extends Pet, S extends PetUpdateModel> {
    void update(T entity, S model);
}
