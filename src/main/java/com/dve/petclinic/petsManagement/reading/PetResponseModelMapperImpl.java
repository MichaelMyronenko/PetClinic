package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.pet.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetResponseModelMapperImpl implements PetResponseModelMapper<PetResponseModel, Pet>{

    @Override
    public PetResponseModel mapToModel(Pet entity) {
        return ImmutablePetResponseModel.builder()
                .petId(entity.getId())
                .petName(entity.getName())
                .build();
    }
}
