package com.dve.petclinic.security.registration.doctorRegistraton;

import com.dve.petclinic.entities.user.CommonUser;

public interface DoctorRegistrationService{
    void registerDoctor(CommonUser user);
}
