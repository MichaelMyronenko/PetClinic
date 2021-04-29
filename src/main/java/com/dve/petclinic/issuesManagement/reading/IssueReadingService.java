package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.doctorsManagement.DoctorFetcher;
import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.issuesManagement.IssueFetcher;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.dve.petclinic.entities.issue.IssueStatus.OPENED;
import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;

@Service
public class IssueReadingService {

    private final IssueResponseModelMapper modelMapper = new IssueResponseModelMapper();
    private final IssueRepository issueRepository;
    private final IssueFetcher issueFetcher;
    private final CurrentUserService currentUserService;
    private final OwnerFetcher ownerFetcher;
    private final DoctorFetcher doctorFetcher;

    public IssueReadingService(IssueRepository issueRepository,
                               IssueFetcher issueFetcher, CurrentUserService currentUserService,
                               OwnerFetcher ownerFetcher,
                               DoctorFetcher doctorFetcher) {
        this.issueRepository = issueRepository;
        this.issueFetcher = issueFetcher;
        this.currentUserService = currentUserService;
        this.ownerFetcher = ownerFetcher;
        this.doctorFetcher = doctorFetcher;
    }

    public List<IssueResponseModel> findIssuesForOwner(Pageable pageable) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());

        return issueRepository.findAllByCreatedBy(pageable, owner).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public List<IssueResponseModel> findIssuesForDoctor(Pageable pageable) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Doctor doctor = doctorFetcher.fetchDoctorByUserId(user.getUserId());

        return issueRepository.findAllByAssignedToOrStatus(pageable, doctor, OPENED).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public IssueResponseModel getIssue(Long issueId) {
        Issue issue = issueFetcher.fetchIssueById(issueId);
        AuthenticatedUser user = currentUserService.getCurrentUser();

        if (user.equalsToUser(issue.getCreatedBy().getUser()) || user.hasRole(DOCTOR)) {
            return modelMapper.mapToModel(issue);
        } else {
            throw new ForbiddenException("",
                    "Rejected get operation due to attempt to see the issue with id: " + issueId +
                            " by user that does not have permissions to see the issue, username: " + user.getUsername(),
                    null);
        }
    }
}
