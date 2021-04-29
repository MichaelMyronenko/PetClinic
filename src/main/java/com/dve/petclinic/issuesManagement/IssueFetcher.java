package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class IssueFetcher {

    private final IssueRepository issueRepository;

    public IssueFetcher(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue fetchIssueById(Long issueId) {
        return issueRepository.findById(issueId).orElseThrow(() -> new NotFoundException(
                "NotFound.IssueFetcher.fetchIssueById",
                "Not found issue by id: " + issueId,
                null
        ));
    }
}
