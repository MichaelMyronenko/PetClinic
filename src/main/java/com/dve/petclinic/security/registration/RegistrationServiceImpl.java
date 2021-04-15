package com.dve.petclinic.security.registration;

import com.dve.petclinic.security.registration.doctorRegistraton.DoctorRegistrationService;
import com.dve.petclinic.security.registration.ownerRegistration.OwnerRegistrationService;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationModel;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationService;
import com.dve.petclinic.user.CommonUser;
import com.dve.petclinic.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dve.petclinic.user.role.RoleName.DOCTOR;
import static com.dve.petclinic.user.role.RoleName.OWNER;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRegistrationService userRegistrationService;
    private final DoctorRegistrationService doctorRegistrationService;
    private final OwnerRegistrationService ownerRegistrationService;

    @Override
    @Transactional
    public void register(UserRegistrationModel registrationModel) {
        User user = userRegistrationService.registerUser(registrationModel);
        if (user.hasRole(OWNER)) {
            ownerRegistrationService.registerOwner((CommonUser) user);
        }
        if (user.hasRole(DOCTOR)) {
            doctorRegistrationService.registerDoctor((CommonUser) user);
        }
    }
}
