package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.creation.IssueCreationModel;
import com.dve.petclinic.issuesManagement.creation.IssueCreationService;
import com.dve.petclinic.issuesManagement.reading.IssueReadingService;
import com.dve.petclinic.issuesManagement.reading.IssueResponseModel;
import com.dve.petclinic.issuesManagement.update.IssueUpdateModel;
import com.dve.petclinic.issuesManagement.update.IssueUpdateService;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void createIssue(IssueCreationModel creationModel, AuthenticatedUser user) {
        issueCreationService.create(creationModel, user);
    }

    public void editIssue(Long issueId, IssueUpdateModel updateModel, AuthenticatedUser user) {
        issueUpdateService.edit(issueId, updateModel, user);
    }

    public List<IssueResponseModel> findIssuesForOwner(AuthenticatedUser user) {
        int numOfPage = 0;
        int pageSize = 10;

        return issueReadingService.findIssuesForOwner(PageRequest.of(numOfPage, pageSize), user);
    }

    public List<IssueResponseModel> findIssuesForDoctor(AuthenticatedUser user) {
        int numOfPage = 0;
        int pageSize = 10;

        return issueReadingService.findIssuesForDoctor(PageRequest.of(numOfPage, pageSize), user);
    }

    public void assignIssueToMe(Long issueId, AuthenticatedUser user) {
        issueUpdateService.assignToMe(issueId, user);
    }

    public void unAssignIssue(Long issueId, AuthenticatedUser user) {
        issueUpdateService.unAssign(issueId, user);
    }

    public void closeIssue(Long issueId, AuthenticatedUser user) {
        issueUpdateService.closeIssue(issueId, user);
    }
}
