package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;
import com.dve.petclinic.entities.issue.IssueStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class IssueCreationModelMapperImpl
        implements IssueCreationModelMapper<Issue, IssueCreationModel> {
    @Override
    public Issue mapToEntity(IssueCreationModel model) {
        return Issue.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .status(IssueStatus.OPENED)
                .build();
    }
}
