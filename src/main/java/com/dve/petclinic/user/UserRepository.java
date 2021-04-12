package com.dve.petclinic.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CommonUser, Long> {

}
