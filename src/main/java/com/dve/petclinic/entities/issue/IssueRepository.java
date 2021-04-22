package com.dve.petclinic.entities.issue;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllByPet(Pet pet);

    Page<Issue> findAllByCreatedBy(Pageable pageable, Owner owner);

    Page<Issue> findAllByAssignedToOrStatus(Pageable pageable, Doctor doctor, IssueStatus status);
}
