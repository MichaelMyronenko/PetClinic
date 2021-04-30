package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.creation.IssueCreationModel;
import com.dve.petclinic.issuesManagement.creation.IssueCreationService;
import com.dve.petclinic.issuesManagement.reading.IssueReadingService;
import com.dve.petclinic.issuesManagement.reading.IssueResponseModel;
import com.dve.petclinic.issuesManagement.update.IssueUpdateModel;
import com.dve.petclinic.issuesManagement.update.IssueUpdateService;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private final IssueCreationService issueCreationService;
    private final IssueUpdateService issueUpdateService;
    private final IssueReadingService issueReadingService;

    public IssueService(IssueCreationService issueCreationService, IssueUpdateService issueUpdateService, IssueReadingService issueReadingService) {
        this.issueCreationService = issueCreationService;
        this.issueUpdateService = issueUpdateService;
        this.issueReadingService = issueReadingService;
    }

    public void createIssue(IssueCreationModel creationModel) {
        issueCreationService.create(creationModel);
    }

    public void editIssue(Long issueId, IssueUpdateModel updateModel) {
        issueUpdateService.edit(issueId, updateModel);
    }

    public IssueResponseModel getIssue(Long issueId) {
        return issueReadingService.getIssue(issueId);
    }

    public Page<IssueResponseModel> findIssuesForOwner(Pageable pageable) {
        return issueReadingService.findIssuesForOwner(pageable);
    }

    public Page<IssueResponseModel> findIssuesForDoctor(Pageable pageable) {
        return issueReadingService.findIssuesForDoctor(pageable);
    }

    public void assignIssueToMe(Long issueId) {
        issueUpdateService.assignToMe(issueId);
    }

    public void unAssignIssue(Long issueId, AuthenticatedUser user) {
        issueUpdateService.unAssign(issueId);
    }

    public void closeIssue(Long issueId, AuthenticatedUser user) {
        issueUpdateService.closeIssue(issueId);
    }
}
