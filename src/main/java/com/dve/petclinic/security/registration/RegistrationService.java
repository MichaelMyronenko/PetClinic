package com.dve.petclinic.security.registration;

import com.dve.petclinic.security.registration.userRegistration.UserRegistrationModel;

public interface RegistrationService {
    void register(UserRegistrationModel registrationModel);
}
