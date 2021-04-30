package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.issue.IssueStatus;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class IssueCreationService {

    private final IssueRepository issueRepository;
    private final OwnerFetcher ownerFetcher;
    private final PetFetcher petFetcher;
    private final CurrentUserService currentUserService;

    public IssueCreationService(IssueRepository issueRepository, OwnerFetcher ownerFetcher, PetFetcher petFetcher, CurrentUserService currentUserService) {
        this.issueRepository = issueRepository;
        this.ownerFetcher = ownerFetcher;
        this.petFetcher = petFetcher;
        this.currentUserService = currentUserService;
    }

    public void create(IssueCreationModel creationModel) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Pet pet = petFetcher.fetchPetById(creationModel.getPetId());
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());
        Issue issue = creationModel.toEntity();

        issue.setCreatedBy(owner);
        issue.setStatus(IssueStatus.OPENED);
        issue.setPet(pet);
        issueRepository.save(issue);
    }
}
