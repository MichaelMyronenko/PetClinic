package com.dve.petclinic.security.registration.constraints;

import com.dve.petclinic.security.registration.CommonUserRegistrationModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CommonUserRegistrationModel.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        CommonUserRegistrationModel user = (CommonUserRegistrationModel) target;
//        int min = 3;
//        int max = 20;
//
//        if (user.getPassword().length() < min || user.getPassword().length() > max) {
//            errors.rejectValue("password", "Size.userForm.password", new Object[] {min, max}, "password");
//        }
    }
}
