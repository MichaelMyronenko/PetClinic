package com.dve.petclinic.doctorsManagement;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.doctor.DoctorRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DoctorFetcher {

    private final DoctorRepository doctorRepository;

    public DoctorFetcher(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor fetchDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException(
                "NotFound.DoctorFetcher.fetchDoctorById",
                "Not found doctor by id: " + doctorId,
                null
        ));
    }

    public Doctor fetchDoctorByUserId(Long userId) {
        return doctorRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(
                "NotFound.DoctorFetcher.fetchDoctorByUserId",
                "Not found doctor by user id: " + userId,
                null
        ));
    }
}
