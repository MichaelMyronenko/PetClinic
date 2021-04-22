package com.dve.petclinic.issuesManagement.update;

import com.dve.petclinic.entities.issue.Issue;

public interface IssueUpdateModelMapper<T extends Issue, S extends IssueUpdateModel> {
    void updateEntity(T entity, S updateModel);
}
