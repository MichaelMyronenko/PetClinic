package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.pet.Pet;

public class PetResponseModelMapper {

    public PetResponseModel mapToModel(Pet entity) {
        return ImmutablePetResponseModel.builder()
                .petId(entity.getId())
                .petName(entity.getName())
                .build();
    }
}
