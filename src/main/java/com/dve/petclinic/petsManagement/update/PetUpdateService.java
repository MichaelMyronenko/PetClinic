package com.dve.petclinic.petsManagement.update;

import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class PetUpdateService {

    private final PetRepository petRepository;
    private final CurrentUserService currentUserService;
    private final PetFetcher petFetcher;

    public PetUpdateService(PetRepository petRepository, CurrentUserService currentUserService, PetFetcher petFetcher) {
        this.petRepository = petRepository;
        this.currentUserService = currentUserService;
        this.petFetcher = petFetcher;
    }

    public void update(Long petId, PetUpdateModel model) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Pet pet = petFetcher.fetchPetById(petId);

        if (user.equalsToUser(pet.getOwner().getUser())) {
            model.updateEntity(pet);
        } else {
            throw new ForbiddenException("Forbidden.PetUpdateService.update",
                    "Update rejected due to attempt to update pet entity with id: " + petId +
                            " that does not belong to the current user, username: " + user.getUsername(),
                    null);
        }

        petRepository.save(pet);
    }
}
