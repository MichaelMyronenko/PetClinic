package com.dve.petclinic.entities.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<CommonRole, Long> {

    Optional<CommonRole> findByRoleName(RoleName roleName);
}
