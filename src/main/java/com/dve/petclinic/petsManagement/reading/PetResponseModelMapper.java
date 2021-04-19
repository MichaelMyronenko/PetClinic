package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.pet.Pet;

public interface PetResponseModelMapper <T extends PetResponseModel, S extends Pet>{
    public T mapToModel(S entity);
}
