package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class PetCreationService {

    private final PetRepository petRepository;
    private final OwnerFetcher ownerFetcher;
    private final CurrentUserService currentUserService;

    public PetCreationService(PetRepository petRepository, OwnerFetcher ownerFetcher, CurrentUserService currentUserService) {
        this.petRepository = petRepository;
        this.ownerFetcher = ownerFetcher;
        this.currentUserService = currentUserService;
    }

    public void create(PetCreationModel creationModel) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());
        Pet pet = creationModel.toEntity();

        pet.setOwner(owner);
        petRepository.save(pet);
    }
}
