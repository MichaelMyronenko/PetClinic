package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.doctorsManagement.reading.DoctorResponseModel;
import com.dve.petclinic.entities.issue.IssueStatus;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
public abstract class IssueResponseModel {

    public abstract Long getIssueId();

    public abstract String getIssueTitle();

    @Nullable
    public abstract String getIssueDescription();

    @Nullable
    public abstract DoctorResponseModel getDoctor();

    public abstract IssueStatus getIssueStatus();
}
