package com.dve.petclinic.user.role;

import org.springframework.security.core.GrantedAuthority;

public interface Role extends GrantedAuthority {
    RoleName getRoleName();
    Long getId();
}
