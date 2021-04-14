package com.dve.petclinic.security.registration.doctorRegistraton;

import com.dve.petclinic.doctor.CommonDoctor;
import com.dve.petclinic.doctor.DoctorRepository;
import com.dve.petclinic.user.CommonUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {
    private final DoctorRepository doctorRepository;

    @Override
    public void registerDoctor(CommonUser user) {
        CommonDoctor doctor = CommonDoctor.builder().user(user).build();
        doctorRepository.save(doctor);
    }
}
