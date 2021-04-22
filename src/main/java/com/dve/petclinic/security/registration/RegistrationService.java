package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.user.CommonUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;
import static com.dve.petclinic.entities.user.role.RoleName.OWNER;

@Service
public class RegistrationService {

    private final UserRegistrationService userRegistrationService;
    private final DoctorRegistrationService doctorRegistrationService;
    private final OwnerRegistrationService ownerRegistrationService;

    public RegistrationService(UserRegistrationService userRegistrationService,
                               DoctorRegistrationService doctorRegistrationService,
                               OwnerRegistrationService ownerRegistrationService) {
        this.userRegistrationService = userRegistrationService;
        this.doctorRegistrationService = doctorRegistrationService;
        this.ownerRegistrationService = ownerRegistrationService;
    }

    @Transactional
    public void register(CommonUserRegistrationModel registrationModel) {
        CommonUser user = userRegistrationService.registerUser(registrationModel);

        if (user.hasRole(OWNER)) {
            ownerRegistrationService.registerOwner(user);
        }

        if (user.hasRole(DOCTOR)) {
            doctorRegistrationService.registerDoctor(user);
        }
    }
}
