package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetUpdateModelMapperImpl implements PetUpdateModelMapper<Pet, PetUpdateModel> {
    @Override
    public void update(Pet entity, PetUpdateModel model) {
        entity.setName(model.getName());
    }
}
