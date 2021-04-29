package com.dve.petclinic.issuesManagement.update;

import com.dve.petclinic.doctorsManagement.DoctorFetcher;
import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.issuesManagement.IssueFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Service;

import static com.dve.petclinic.entities.issue.IssueStatus.*;
import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;

@Service
public class IssueUpdateService {

    private final IssueRepository issueRepository;
    private final CurrentUserService currentUserService;
    private final DoctorFetcher doctorFetcher;
    private final IssueFetcher issueFetcher;

    public IssueUpdateService(IssueRepository issueRepository, CurrentUserService currentUserService, DoctorFetcher doctorFetcher, IssueFetcher issueFetcher) {
        this.issueRepository = issueRepository;
        this.currentUserService = currentUserService;
        this.doctorFetcher = doctorFetcher;
        this.issueFetcher = issueFetcher;
    }

    public void edit(Long issueId, IssueUpdateModel editModel) {
        Issue issue = issueFetcher.fetchIssueById(issueId);
        AuthenticatedUser user = currentUserService.getCurrentUser();

        if (!(user.equalsToUser(issue.getCreatedBy().getUser()))) {
            throw new ForbiddenException("You can't change issues created by another user!",
                    "",
                    null);
        }

        if (issue.getStatus().equals(CLOSED)) {
            throw new ConflictException("You can't change closed issues",
                    "",
                    null);
        }

        editModel.updateEntity(issue);
        issueRepository.save(issue);
    }

    public void assignToMe(Long issueId) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Issue issue = issueFetcher.fetchIssueById(issueId);
        Doctor doctor = doctorFetcher.fetchDoctorByUserId(user.getUserId());

        if (!user.hasRole(DOCTOR)) {
            throw new ForbiddenException("you can't assign the issue to you!",
                    "",
                    null);
        }

        if (issue.getAssignedTo() == null && issue.getStatus().equals(OPENED)) {
            issue.setAssignedTo(doctor);
            issue.setStatus(IN_PROCESS);
        } else if (issue.getAssignedTo().equals(doctor)) {
            throw new ConflictException("This issue is already assigned to you!",
                    "",
                    null);
        } else if (!issue.getAssignedTo().equals(doctor)) {
            throw new ForbiddenException("The issue is already assigned to another doctor!",
                    "",
                    null);
        }

        issueRepository.save(issue);
    }

    public void unAssign(Long issueId) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Issue issue = issueFetcher.fetchIssueById(issueId);
        Doctor doctor = doctorFetcher.fetchDoctorByUserId(user.getUserId());

        if (issue.getStatus().equals(CLOSED)) {
            throw new ConflictException("The issue is closed",
                    "",
                    null);
        }

        if (user.equalsToUser(issue.getCreatedBy().getUser())
                || issue.getAssignedTo().equals(doctor)) {
            issue.setAssignedTo(null);
            issue.setStatus(OPENED);
        }

        issueRepository.save(issue);
    }

    public void closeIssue(Long issueId) {
        Issue issue = issueFetcher.fetchIssueById(issueId);
        AuthenticatedUser user = currentUserService.getCurrentUser();

        if (user.equalsToUser(issue.getCreatedBy().getUser())) {
            if (issue.getStatus().equals(CLOSED)) {
                throw new ConflictException("The issue is already closed!",
                        "",
                        null);
            }
            issue.setStatus(CLOSED);
        } else {
            throw new ForbiddenException("You can't close the issue!",
                    "",
                    null);
        }

        issueRepository.save(issue);
    }
}
