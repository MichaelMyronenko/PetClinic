package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.entities.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final InitRolesResolver rolesResolver;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder encoder, InitRolesResolver rolesResolver) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.rolesResolver = rolesResolver;
    }

    public CommonUser registerUser(CommonUserRegistrationModel userModel) {
        CommonUser user = new CommonUser(userModel.getUsername(),
                encoder.encode(userModel.getPassword()),
                rolesResolver.getRoles(userModel.getSecretDoctorsKey()),
                true);

        return userRepository.save(user);
    }
}
