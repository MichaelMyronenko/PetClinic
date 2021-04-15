package com.dve.petclinic.security.registration.userRegistration;

import com.dve.petclinic.generalExceptions.InvalidCredentialsException;
import com.dve.petclinic.security.SecurityProperties;
import com.dve.petclinic.user.role.CommonRole;
import com.dve.petclinic.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.dve.petclinic.user.role.RoleName.*;
import static com.dve.petclinic.user.role.RoleName.ADMIN;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@AllArgsConstructor
public class InitRolesResolver {
    RoleRepository roleRepository;
    SecurityProperties securityProperties;

    Set<CommonRole> getRoles(String secretDoctorsKey) {
        Set<CommonRole> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(USER).orElseThrow());
        roles.add(roleRepository.findByRoleName(OWNER).orElseThrow());
        if (isBlank(secretDoctorsKey)) {
            return roles;
        } else if (secretDoctorsKey.equals(securityProperties.getSecretDoctorsKey())) {
            roles.add(roleRepository.findByRoleName(DOCTOR).orElseThrow());
            roles.add(roleRepository.findByRoleName(ADMIN).orElseThrow());
            return roles;
        } else {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
    }
}
