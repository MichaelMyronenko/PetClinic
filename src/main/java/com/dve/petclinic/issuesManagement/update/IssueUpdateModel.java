package com.dve.petclinic.issuesManagement.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueUpdateModel {
    @Size(max = 250)
    private String title;

    @Size(max = 1000)
    private String description;
}
