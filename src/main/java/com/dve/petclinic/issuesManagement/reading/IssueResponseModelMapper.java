package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.entities.issue.Issue;

public class IssueResponseModelMapper {

    public IssueResponseModel mapToModel(Issue entity) {
        return ImmutableIssueResponseModel.builder()
                .issueId(entity.getId())
                .issueTitle(entity.getTitle())
                .issueDescription(entity.getDescription())
                .issueStatus(entity.getStatus())
                .doctorId(entity.getAssignedTo() != null
                        ? entity.getAssignedTo().getId()
                        : null)
                .petId(entity.getPet().getId())
                .createdById(entity.getCreatedBy().getId())
                .build();
    }
}
