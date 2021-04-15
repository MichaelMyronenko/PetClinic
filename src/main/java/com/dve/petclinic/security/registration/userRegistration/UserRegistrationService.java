package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.user.CommonUser;
import com.dve.petclinic.user.UserService;

public interface UserRegistrationService extends UserService {
    CommonUser registerUser(UserRegistrationModel userModel);
}
