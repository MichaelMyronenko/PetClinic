package com.dve.petclinic.entities.user.role;

import org.springframework.security.core.GrantedAuthority;

public interface Role extends GrantedAuthority {
    RoleName getRoleName();

    Long getId();
}
