package com.dve.petclinic.entities.issue;

import com.dve.petclinic.entities.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByPet(Pet pet);
}
