package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class PetCreationService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetCreationService(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    public void create(PetCreationModel creationModel, AuthenticatedUser user) {
        Owner owner = fetchOwnerByUserId(user.getUserId());
        Pet pet = creationModel.toEntity();

        pet.setOwner(owner);
        petRepository.save(pet);
    }

    private Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("not found owner!"));
    }
}
