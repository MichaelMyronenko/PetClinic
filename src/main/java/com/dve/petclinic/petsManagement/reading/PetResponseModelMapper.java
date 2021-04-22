package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.pet.Pet;

public interface PetResponseModelMapper<T extends PetResponseModel, S extends Pet> {
    T mapToModel(S entity);
}
