package com.dve.petclinic.security;

import java.util.Set;

import static com.dve.petclinic.entities.user.role.RoleName.*;

public final class AuthenticatedUserProvider {

    public static AuthenticatedUser getTestAuthenticatedDoctor() {
        return new AuthenticatedUser(CommonUserProvider.getCommonUser(Set.of(USER, OWNER, DOCTOR, ADMIN)));
    }

    public static AuthenticatedUser getTestAuthenticatedOwner() {
        return new AuthenticatedUser(CommonUserProvider.getCommonUser());
    }
}
