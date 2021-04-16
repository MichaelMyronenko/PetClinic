package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PetUpdateService {
    PetUpdateModelMapper<Pet, PetUpdateModel> modelMapper;
    PetRepository petRepository;

    public void update(Long petId, PetUpdateModel model, AuthenticatedUser user) {
        Pet pet = fetchPet(petId);

        if (user.equalsToUser(pet.getOwner().getUser())) {
            modelMapper.update(pet, model);
        } else {
            throw new ForbiddenException("You can't edit this pet, It is not yours!!!");
        }
    }

    public Pet fetchPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet with id " + petId));
    }
}
