package com.dve.petclinic.user;

import com.dve.petclinic.user.role.Role;
import com.dve.petclinic.user.role.RoleName;

import java.util.Set;

public interface User {
    Long getId();

    String getUsername();

    String getPassword();

    Set<? extends Role> getRoles();

    boolean hasRole(RoleName roleName);

    boolean isActive();
}
