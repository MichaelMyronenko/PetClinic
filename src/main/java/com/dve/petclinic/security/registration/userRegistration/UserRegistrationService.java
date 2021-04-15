package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.entities.user.UserService;

public interface UserRegistrationService extends UserService {
    CommonUser registerUser(UserRegistrationModel userModel);
}
