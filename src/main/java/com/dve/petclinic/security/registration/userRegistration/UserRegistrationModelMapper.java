package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.entities.user.User;

public interface UserRegistrationModelMapper<T extends User, S extends UserRegistrationModel> {
    T mapToEntity(S model);
}