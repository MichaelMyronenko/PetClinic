package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.entities.issue.Issue;

public interface IssueResponseModelMapper<T extends IssueResponseModel, S extends Issue> {
    T mapToModel(S entity);
}
