package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.pet.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetCreationModelMapperImpl implements PetCreationModelMapper<Pet, PetCreationModel> {
    @Override
    public Pet mapToEntity(PetCreationModel model) {
        return Pet.builder()
                .name(model.name)
                .build();
    }
}