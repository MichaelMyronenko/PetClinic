package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.security.registration.doctorRegistraton.DoctorRegistrationService;
import com.dve.petclinic.security.registration.ownerRegistration.OwnerRegistrationService;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationModel;
import com.dve.petclinic.security.registration.userRegistration.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;
import static com.dve.petclinic.entities.user.role.RoleName.OWNER;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRegistrationService userRegistrationService;
    private final DoctorRegistrationService doctorRegistrationService;
    private final OwnerRegistrationService ownerRegistrationService;

    @Override
    @Transactional
    public void register(UserRegistrationModel registrationModel) {
        CommonUser user = userRegistrationService.registerUser(registrationModel);
        if (user.hasRole(OWNER)) {
            ownerRegistrationService.registerOwner(user);
        }
        if (user.hasRole(DOCTOR)) {
            doctorRegistrationService.registerDoctor(user);
        }
    }
}
