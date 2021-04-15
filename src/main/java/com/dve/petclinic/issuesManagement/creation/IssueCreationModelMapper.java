package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;

public interface IssueCreationModelMapper<T extends Issue, S extends IssueCreationModel> {
    T mapToEntity(S model);
}
