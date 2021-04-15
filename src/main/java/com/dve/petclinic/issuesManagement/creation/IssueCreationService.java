package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssueCreationService {
    private final IssueRepository issueRepository;
    private final OwnerRepository ownerRepository;
    private final IssueCreationModelMapper<Issue, IssueCreationModel> creationModelMapper;

    public void create(IssueCreationModel creationModel, AuthenticatedUser user) {
        Issue issue = creationModelMapper.mapToEntity(creationModel);
        issue.setCreatedBy(fetchOwnerByUserId(user.getUserId()));
        issueRepository.save(issue);
    }

    private Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found owner!"));
    }
}
