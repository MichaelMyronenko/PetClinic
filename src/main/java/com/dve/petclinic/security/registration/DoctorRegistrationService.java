package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.doctor.DoctorRepository;
import com.dve.petclinic.entities.user.CommonUser;
import org.springframework.stereotype.Service;

@Service
public class DoctorRegistrationService {

    private final DoctorRepository doctorRepository;

    public DoctorRegistrationService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void registerDoctor(CommonUser user) {
        Doctor doctor = new Doctor(user);

        doctorRepository.save(doctor);
    }
}
