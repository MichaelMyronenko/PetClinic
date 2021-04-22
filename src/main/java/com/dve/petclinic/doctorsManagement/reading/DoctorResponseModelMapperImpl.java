package com.dve.petclinic.doctorsManagement.reading;

import com.dve.petclinic.entities.doctor.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorResponseModelMapperImpl implements DoctorResponseModelMapper<DoctorResponseModel, Doctor> {
    @Override
    public DoctorResponseModel mapToModel(Doctor entity) {
        return ImmutableDoctorResponseModel.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .position(entity.getPosition())
                .build();
    }
}