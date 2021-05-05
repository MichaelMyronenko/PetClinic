package com.dve.petclinic.petsManagement;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.owner.OwnerProvider;
import com.dve.petclinic.petsManagement.reading.ImmutablePetResponseModel;
import com.dve.petclinic.petsManagement.reading.PetResponseModel;

import java.util.ArrayList;
import java.util.List;

public final class PetProvider {

    public static PetResponseModel getFirstPetResponseModel() {
        return ImmutablePetResponseModel.builder()
                .petId(1L)
                .petName("FirstTuzik")
                .build();
    }

    public static PetResponseModel getSecPetResponseModel() {
        return ImmutablePetResponseModel.builder()
                .petId(2L)
                .petName("SecondTuzik")
                .build();
    }

    public static PetResponseModel getSomePet(Long numOfPet) {
        return ImmutablePetResponseModel.builder()
                .petId(numOfPet)
                .petName("TuzikNum" + numOfPet)
                .build();
    }

    public static Pet getSomePetEntity(Long numOfPet) {
        return new Pet(numOfPet, "testTuzik", OwnerProvider.getOwner());
    }

    public static Pet getSomePetEntity(Owner owner) {
        return new Pet("testTuzik", owner);
    }

    public static Pet getSomePetEntity() {
        return new Pet("testTuzik", OwnerProvider.getOwner());
    }

    public static List<PetResponseModel> getTestPets(int numOfPets) {
        List<PetResponseModel> petList = new ArrayList<>();

        for (int i = 0; i < numOfPets; i++) {
            petList.add(getSomePet((long) (i + 1)));
        }

        return petList;
    }

    public static List<Pet> getTestPetEntities(int numOfPets) {
        List<Pet> petList = new ArrayList<>();

        for (int i = 0; i < numOfPets; i++) {
            petList.add(getSomePetEntity((long) (i + 1)));
        }

        return petList;
    }
}
