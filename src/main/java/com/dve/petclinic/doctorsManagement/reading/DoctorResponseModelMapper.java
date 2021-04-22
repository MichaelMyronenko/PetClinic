package com.dve.petclinic.doctorsManagement.reading;

import com.dve.petclinic.entities.doctor.Doctor;

public class DoctorResponseModelMapper {

    public DoctorResponseModel mapToModel(Doctor entity) {
        return ImmutableDoctorResponseModel.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .position(entity.getPosition())
                .build();
    }
}