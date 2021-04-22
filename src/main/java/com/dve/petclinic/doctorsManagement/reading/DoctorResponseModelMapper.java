package com.dve.petclinic.doctorsManagement.reading;

import com.dve.petclinic.entities.doctor.Doctor;

public interface DoctorResponseModelMapper<T extends DoctorResponseModel, S extends Doctor> {
    T mapToModel(S entity);
}
