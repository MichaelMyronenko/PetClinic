package com.dve.petclinic.security.registration.doctorRegistraton;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.doctor.DoctorRepository;
import com.dve.petclinic.entities.user.CommonUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {
    private final DoctorRepository doctorRepository;

    @Override
    public void registerDoctor(CommonUser user) {
        Doctor doctor = Doctor.builder().user(user).build();
        doctorRepository.save(doctor);
    }
}
