package com.dve.petclinic.petsManagement;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.owner.OwnerProvider;
import com.dve.petclinic.security.CommonUserProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Test
    void findByName_thenReturnPet() {
        CommonUser user = CommonUserProvider.getCommonUser();
        Owner owner = OwnerProvider.getOwner(user);
        Pet pet = PetProvider.getSomePetEntity(owner);
        entityManager.persist(user);
        entityManager.persist(owner);
        entityManager.persist(pet);
        entityManager.flush();

        Pet found = petRepository.findById(6L).get();

        assertThat(found.getName()).isEqualTo(pet.getName());
    }
}
