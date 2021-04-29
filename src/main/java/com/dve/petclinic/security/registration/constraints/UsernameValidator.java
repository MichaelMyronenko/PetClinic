package com.dve.petclinic.security.registration.constraints;

import com.dve.petclinic.entities.user.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findCommonUserByUsername(value).isEmpty();
    }

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}