package com.dve.petclinic.security;

import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.entities.user.role.CommonRole;
import com.dve.petclinic.entities.user.role.RoleName;

import java.util.Set;
import java.util.stream.Collectors;

import static com.dve.petclinic.entities.user.role.RoleName.OWNER;
import static com.dve.petclinic.entities.user.role.RoleName.USER;

public final class CommonUserProvider {

    public static CommonUser getCommonUser() {
        return new CommonUser("testUser",
                "uselessPass",
                Set.of(USER, OWNER).stream().map(CommonUserProvider::mapToCommonRole).collect(Collectors.toSet()),
                true);
    }

    public static CommonUser getCommonUser(Set<RoleName> roles) {
        return new CommonUser("testUser",
                "uselessPass",
                roles.stream().map(CommonUserProvider::mapToCommonRole).collect(Collectors.toSet()),
                true);
    }

    public static CommonUser getCommonUser(String username) {
        return new CommonUser(username,
                "uselessPass",
                Set.of(USER, OWNER).stream().map(CommonUserProvider::mapToCommonRole).collect(Collectors.toSet()),
                true);
    }

    public static CommonUser getCommonUser(String username, Set<RoleName> roles) {
        return new CommonUser(username,
                "uselessPass",
                roles.stream().map(CommonUserProvider::mapToCommonRole).collect(Collectors.toSet()),
                true);
    }

    private static CommonRole mapToCommonRole(RoleName roleName) {
        return new CommonRole(1L, roleName);
    }
}
