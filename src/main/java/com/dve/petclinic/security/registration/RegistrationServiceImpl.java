package com.dve.petclinic.security.registration;

import com.dve.petclinic.security.registration.doctorRegistraton.DoctorRegistrationService;
import com.dve.petclinic.security.registration.userRegistration.CommonUserRegistrationModel;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationModel;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationService;
import com.dve.petclinic.user.CommonUser;
import com.dve.petclinic.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dve.petclinic.user.role.RoleName.DOCTOR;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRegistrationService userRegistrationService;
    private final DoctorRegistrationService doctorRegistrationService;

    @Override
    @Transactional
    public void register(UserRegistrationModel registrationModel) {
        User user = userRegistrationService.registerUser(registrationModel);

        if (user.hasRole(DOCTOR)) {
            doctorRegistrationService.registerDoctor((CommonUser) user);
        }
    }
}
