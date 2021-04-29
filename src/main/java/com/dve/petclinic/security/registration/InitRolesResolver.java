package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.user.role.CommonRole;
import com.dve.petclinic.entities.user.role.RoleRepository;
import com.dve.petclinic.generalExceptions.InvalidCredentialsException;
import com.dve.petclinic.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.dve.petclinic.entities.user.role.RoleName.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class InitRolesResolver {

    private final RoleRepository roleRepository;
    private final SecurityProperties securityProperties;

    public InitRolesResolver(RoleRepository roleRepository, SecurityProperties securityProperties) {
        this.roleRepository = roleRepository;
        this.securityProperties = securityProperties;
    }

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
            throw new InvalidCredentialsException("InvalidCredentials.initRoles.getRoles",
                    "Registration failed due to invalid secret doctor's key '" + secretDoctorsKey + "'",
                    null);
        }
    }
}
