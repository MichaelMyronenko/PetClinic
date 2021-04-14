package com.dve.petclinic.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<CommonRole, Long> {
    Optional<CommonRole> findByRoleName(RoleName roleName);
}
