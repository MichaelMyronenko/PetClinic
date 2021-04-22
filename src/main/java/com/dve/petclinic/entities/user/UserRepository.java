package com.dve.petclinic.entities.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CommonUser, Long> {

    @EntityGraph("user.roles")
    Optional<CommonUser> findCommonUserByUsername(String username);

    @EntityGraph("user.roles")
    Optional<CommonUser> findById(Long userId);
}
