package com.dve.petclinic.petsManagement.delete;

import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class PetDeleteService {

    private final PetFetcher petFetcher;
    private final IssueRepository issueRepository;
    private final PetRepository petRepository;
    private final CurrentUserService currentUserService;

    public PetDeleteService(PetFetcher petFetcher, IssueRepository issueRepository, PetRepository petRepository, CurrentUserService currentUserService) {
        this.petFetcher = petFetcher;
        this.issueRepository = issueRepository;
        this.petRepository = petRepository;
        this.currentUserService = currentUserService;
    }

    public void delete(Long petId) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Pet pet = petFetcher.fetchPetById(petId);

        if (!(user.equalsToUser(pet.getOwner().getUser()))) {
            throw new ForbiddenException("Forbidden.PetDeleteService.delete",
                    "forbidden due to an attempt to delete the pet with id: " + petId +
                            "that does not belong to the current user, username: " + user.getUsername(),
                    null);
        }

        if (!(issueRepository.findAllByPet(pet).isEmpty())) {
            throw new ConflictException("Conflict.PetDeleteService.delete",
                    "Deletion rejected! The pet entity is is bind with some issues",
                    null);
        }

        petRepository.delete(pet);
    }
}
