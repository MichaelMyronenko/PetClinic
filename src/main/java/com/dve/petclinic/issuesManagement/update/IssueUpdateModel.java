package com.dve.petclinic.issuesManagement.update;

import com.dve.petclinic.entities.issue.Issue;

import javax.validation.constraints.Size;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class IssueUpdateModel {

    @Size(max = 250)
    private String title;

    @Size(max = 1000)
    private String description;

    public void updateEntity(Issue issue) {
        issue.setTitle(isNotBlank(title) ? title : issue.getTitle());
        issue.setDescription(isNotBlank(description) ? description : issue.getDescription());
    }

    public IssueUpdateModel() {
    }

    public IssueUpdateModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
