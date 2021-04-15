package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.entities.user.CommonUser;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommonUserRegistrationModelMapper
        implements UserRegistrationModelMapper<CommonUser, UserRegistrationModel> {
    private final PasswordEncoder encoder;
    private final InitRolesResolver rolesResolver;

    @Override
    public CommonUser mapToEntity(UserRegistrationModel model) {
        return CommonUser.builder()
                .username(model.getUsername())
                .password(encoder.encode(model.getPassword()))
                .active(true)
                .roles(rolesResolver.getRoles(model.getSecretDoctorsKey()))
                .build();
    }
}
