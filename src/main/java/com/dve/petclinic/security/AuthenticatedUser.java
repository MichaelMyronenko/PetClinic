package com.dve.petclinic.security;


import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.entities.user.role.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthenticatedUser implements UserDetails {

    private final CommonUser user;

    public AuthenticatedUser(CommonUser user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    public boolean equalsToUser(CommonUser anotherUser) {
        return user.getId().equals(anotherUser.getId());
    }

    public boolean hasRole(RoleName role) {
        return user.hasRole(role);
    }
}
