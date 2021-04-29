package com.dve.petclinic.petsManagement;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PetFetcher {

    private final PetRepository petRepository;

    public PetFetcher(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet fetchPetById(Long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new NotFoundException(
                "NotFound.PetFetcher.fetchPetById",
                "Not found pet by id: " + petId,
                null
        ));
    }

    public Pet fetchByIdAndOwner(Long petId, Owner owner) {
        return petRepository.findByIdAndOwner(petId, owner)
                .orElseThrow(() -> new NotFoundException(
                        "NotFound.PetFetcher.fetchPetByIdAndOwner",
                        "Not found pet by id: " + petId + " and owner with id: " + owner.getId(),
                        null
                ));
    }
}
