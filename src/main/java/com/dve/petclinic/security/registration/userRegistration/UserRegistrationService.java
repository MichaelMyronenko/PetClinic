package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.user.User;
import com.dve.petclinic.user.UserService;

public interface UserRegistrationService extends UserService {
    User registerUser(UserRegistrationModel userModel);
}
