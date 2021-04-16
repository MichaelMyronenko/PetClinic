package com.dve.petclinic.petsManagement.delete;

import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetDeleteService {
    private final PetRepository petRepository;
    private final IssueRepository issueRepository;

    public void delete(Long petId, AuthenticatedUser user) {
        Pet pet = fetchPet(petId);
        if (!(user.equalsToUser(pet.getOwner().getUser()))) {
            throw new ForbiddenException("You can't remove this pet, It is not yours!");
        }
        if (!(issueRepository.findAllByPet(pet).isEmpty())) {
            throw new ConflictException("The pet has some issues you can't delete it");
        }
        petRepository.delete(pet);
    }

    private Pet fetchPet(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet with id " + petId));
    }
}
