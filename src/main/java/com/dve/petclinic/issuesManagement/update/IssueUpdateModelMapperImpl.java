package com.dve.petclinic.issuesManagement.update;

import com.dve.petclinic.entities.issue.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueUpdateModelMapperImpl implements IssueUpdateModelMapper<Issue, IssueUpdateModel>{
    @Override
    public void updateEntity(Issue entity, IssueUpdateModel updateModel) {
        entity.setTitle(updateModel.getTitle() != null ? updateModel.getTitle() : entity.getTitle());
        entity.setDescription(updateModel.getDescription() != null
                ? updateModel.getDescription() : entity.getDescription());
    }
}
