package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.issue.Issue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IssueCreationModel {

    @Size(min = 5, max = 255)
    @NotBlank
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    private Long petId;

    public IssueCreationModel() {
    }

    public IssueCreationModel(String title, String description, Long petId) {
        this.title = title;
        this.description = description;
        this.petId = petId;
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

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Issue toEntity() {
        return new Issue(title, description);
    }
}
