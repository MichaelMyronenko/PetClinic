package com.dve.petclinic.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CommonUser, Long> {
    @EntityGraph("user.roles")
    Optional<CommonUser> findCommonUserByUsername(String username);

    @EntityGraph("user.roles")
    Optional<CommonUser> findById(Long userId);
}
