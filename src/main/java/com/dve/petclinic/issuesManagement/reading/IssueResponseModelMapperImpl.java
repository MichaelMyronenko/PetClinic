package com.dve.petclinic.issuesManagement.reading;

import com.dve.petclinic.doctorsManagement.reading.DoctorResponseModel;
import com.dve.petclinic.doctorsManagement.reading.DoctorResponseModelMapper;
import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.issue.Issue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IssueResponseModelMapperImpl implements IssueResponseModelMapper<IssueResponseModel, Issue> {
    private final DoctorResponseModelMapper<DoctorResponseModel, Doctor> doctorModelMapper;

    @Override
    public IssueResponseModel mapToModel(Issue entity) {
        return ImmutableIssueResponseModel.builder()
                .issueId(entity.getId())
                .issueTitle(entity.getTitle())
                .issueDescription(entity.getDescription())
                .issueStatus(entity.getStatus())
                .doctor(entity.getAssignedTo() != null
                        ? doctorModelMapper.mapToModel(entity.getAssignedTo())
                        : null)
                .build();
    }
}
