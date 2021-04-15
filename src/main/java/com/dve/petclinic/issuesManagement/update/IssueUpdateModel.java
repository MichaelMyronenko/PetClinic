package com.dve.petclinic.issuesManagement.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueUpdateModel {
    @Size(min = 5, max = 255)
    @NotBlank
    private String title;
    @Max(1000)
    private String description;
}
