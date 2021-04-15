package com.dve.petclinic.issuesManagement.update;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.doctor.DoctorRepository;
import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dve.petclinic.entities.issue.IssueStatus.*;
import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;

@AllArgsConstructor
@Service
public class IssueUpdateService {
    private final IssueRepository issueRepository;
    private final DoctorRepository doctorRepository;
    private final IssueUpdateModelMapper<Issue, IssueUpdateModel> modelMapper;

    public void edit(Long issueId, IssueUpdateModel editModel, AuthenticatedUser user) {
        Issue issue = fetchIssueById(issueId);

        if (!(user.equalsToUser(issue.getCreatedBy().getUser()))) {
            throw new ForbiddenException("You can't change issues created by another user!");
        }
        if (issue.getStatus().equals(CLOSED)) {
            throw new ConflictException("You can't change closed issues");
        }

        modelMapper.updateEntity(issue, editModel);
    }

    public void assignToMe(Long issueId, AuthenticatedUser user) {
        Issue issue = fetchIssueById(issueId);

        if (!user.hasRole(DOCTOR)) {
            throw new ForbiddenException("you can't assign the issue to you!");
        }

        Doctor doctor = fetchDoctorByUserId(user.getUserId());

        if (issue.getAssignedTo() == null
                && issue.getStatus().equals(OPENED)) {
            issue.setAssignedTo(doctor);
        }
        if (issue.getAssignedTo().equals(doctor)) {
            throw new ConflictException("This issue is already assigned to you!");
        } else if (!issue.getAssignedTo().equals(doctor)) {
            throw new ForbiddenException("The issue is already assigned to another doctor!");
        }
    }

    public void unAssign(Long issueId, AuthenticatedUser user) {
        Issue issue = fetchIssueById(issueId);
        if (issue.getStatus().equals(CLOSED)) {
            throw new ConflictException("The issue is closed");
        }

        Doctor doctor = fetchDoctorByUserId(user.getUserId());

        if (user.equalsToUser(issue.getCreatedBy().getUser())
                || issue.getAssignedTo().equals(doctor)) {
            issue.setAssignedTo(null);
            issue.setStatus(OPENED);
        }
    }

    private Issue fetchIssueById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new NotFoundException("Not found issue with id " + issueId));
    }

    private Doctor fetchDoctorByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found doctor by User Id"));
    }
}
