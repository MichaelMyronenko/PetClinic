package com.dve.petclinic.entities.pet;

import com.dve.petclinic.entities.owner.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findAllByOwner(Pageable pageable, Owner owner);

    Optional<Pet> findByIdAndOwner(Long aLong, Owner owner);
}