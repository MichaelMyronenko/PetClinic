package com.dve.petclinic.security.registration;

import com.dve.petclinic.user.UserService;

public interface UserRegistrationService extends UserService {
    boolean registerUser(UserRegistrationModel userModel);
}
