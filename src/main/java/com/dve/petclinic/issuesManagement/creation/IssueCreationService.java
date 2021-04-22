package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class IssueCreationService {

    private final IssueRepository issueRepository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public IssueCreationService(IssueRepository issueRepository, OwnerRepository ownerRepository, PetRepository petRepository) {
        this.issueRepository = issueRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public void create(IssueCreationModel creationModel, AuthenticatedUser user) {
        Pet pet = fetchPet(creationModel.getPetId());
        Owner owner = fetchOwnerByUserId(user.getUserId());
        Issue issue = creationModel.toEntity();

        issue.setCreatedBy(owner);

        if (!(issue.getCreatedBy().equals(pet.getOwner()))) {
            throw new ForbiddenException("It is not your pet!");
        }

        issue.setPet(pet);
        issueRepository.save(issue);
    }

    private Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found owner!"));
    }

    private Pet fetchPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("not found pet with ID " + petId));
    }
}
