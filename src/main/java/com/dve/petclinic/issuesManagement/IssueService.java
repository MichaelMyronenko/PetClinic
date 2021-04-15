package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.creation.IssueCreationModel;
import com.dve.petclinic.issuesManagement.creation.IssueCreationService;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssueService {
    private final IssueCreationService issueCreationService;

    public void createIssue(IssueCreationModel creationModel, AuthenticatedUser user) {
        issueCreationService.create(creationModel, user);
    }
}
