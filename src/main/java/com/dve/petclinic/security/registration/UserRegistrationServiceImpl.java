package com.dve.petclinic.security.registration;

import com.dve.petclinic.generalExceptions.ConflictException;
import com.dve.petclinic.generalExceptions.InvalidCredentialsException;
import com.dve.petclinic.security.SecurityProperties;
import com.dve.petclinic.user.CommonUser;
import com.dve.petclinic.user.UserRepository;
import com.dve.petclinic.user.role.CommonRole;
import com.dve.petclinic.user.role.RoleName;
import com.dve.petclinic.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

@AllArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SecurityProperties securityProperties;
    private final RoleRepository roleRepository;

    public boolean registerUser(UserRegistrationModel userModel) {
        if (userRepository.findCommonUserByUsername(userModel.getUsername()).isPresent()) {
            throw new ConflictException("User with username " + userModel.getUsername() + " already exists!");
        }

        CommonUser user = CommonUser.builder()
                .username(userModel.getUsername())
                .password(encoder.encode(userModel.getPassword()))
                .active(true)
                .roles(getRoles(userModel.getSecretDoctorsKey()))
                .build();

        userRepository.save(user);
        return true;
    }

    private Set<CommonRole> getRoles(String secretDoctorsKey) {
        Set<CommonRole> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow());
        if (isBlank(secretDoctorsKey)) {
            roles.add(roleRepository.findByRoleName(RoleName.OWNER).orElseThrow());
            return roles;
        } else if (secretDoctorsKey.equals(securityProperties.getSecretDoctorsKey())) {
            roles.add(roleRepository.findByRoleName(RoleName.DOCTOR).orElseThrow());
            roles.add(roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow());
            return roles;
        } else {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
    }
}
