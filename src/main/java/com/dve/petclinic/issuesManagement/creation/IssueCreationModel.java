package com.dve.petclinic.issuesManagement.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueCreationModel {
    @Size(min = 5, max = 255)
    @NotBlank
    private String title;
    @Max(1000)
    private String description;
    @NotNull
    private Long petId;
}
