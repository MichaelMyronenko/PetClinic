package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class PetUpdateService {

    PetRepository petRepository;

    public void update(Long petId, PetUpdateModel model, AuthenticatedUser user) {
        Pet pet = fetchPet(petId);

        if (user.equalsToUser(pet.getOwner().getUser())) {
            model.updateEntity(pet);
        } else {
            throw new ForbiddenException("You can't edit this pet, It is not yours!!!");
        }

        petRepository.save(pet);
    }

    private Pet fetchPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet with id " + petId));
    }
}
