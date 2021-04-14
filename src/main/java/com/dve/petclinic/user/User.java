package com.dve.petclinic.user;

import com.dve.petclinic.user.role.Role;

import java.util.Set;

public interface User {
    Long getId();

    String getUsername();

    String getPassword();

    Set<? extends Role> getRoles();

    boolean isActive();
}
