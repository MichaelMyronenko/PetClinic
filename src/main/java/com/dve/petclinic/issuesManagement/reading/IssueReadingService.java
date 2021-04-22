package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.doctor.DoctorRepository;
import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueRepository;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.generalExceptions.ForbiddenException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.dve.petclinic.entities.issue.IssueStatus.OPENED;
import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;

@Service
@AllArgsConstructor
public class IssueReadingService {
    private final IssueRepository issueRepository;
    private final OwnerRepository ownerRepository;
    private final DoctorRepository doctorRepository;
    private final IssueResponseModelMapper<IssueResponseModel, Issue> modelMapper;

    public List<IssueResponseModel> findIssuesForOwner(Pageable pageable, AuthenticatedUser user) {
        Owner owner = fetchOwnerByUser(user.getUserId());

        return issueRepository.findAllByCreatedBy(pageable, owner).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public List<IssueResponseModel> findIssuesForDoctor(Pageable pageable, AuthenticatedUser user) {
        Doctor doctor = fetchDoctorByUser(user.getUserId());

        return issueRepository.findAllByAssignedToOrStatus(pageable, doctor, OPENED).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public IssueResponseModel getIssue(Long issueId, AuthenticatedUser user) {
        Issue issue = fetchIssueById(issueId);

        if (user.equalsToUser(issue.getCreatedBy().getUser())
                || user.hasRole(DOCTOR)) {
            return modelMapper.mapToModel(issue);
        } else {
            throw new ForbiddenException("You don't have permissions to see the issue!");
        }
    }

    private Owner fetchOwnerByUser(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found owner!"));
    }

    private Doctor fetchDoctorByUser(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("not found doctor!"));
    }

    private Issue fetchIssueById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new NotFoundException("Not found Issue with id " + issueId));
    }
}
