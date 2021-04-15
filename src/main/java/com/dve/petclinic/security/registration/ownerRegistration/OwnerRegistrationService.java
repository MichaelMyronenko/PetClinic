package com.dve.petclinic.security.registration.ownerRegistration;

import com.dve.petclinic.entities.user.CommonUser;

public interface OwnerRegistrationService {
    void registerOwner(CommonUser user);
}
