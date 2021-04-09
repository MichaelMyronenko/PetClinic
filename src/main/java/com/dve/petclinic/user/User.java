package com.dve.petclinic.user;

import com.dve.petclinic.user.role.Role;

public interface User {
    Long getId();

    String getUsername();

    String getPassword();

    Role getRole();
}
