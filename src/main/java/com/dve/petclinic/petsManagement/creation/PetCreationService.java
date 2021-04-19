package com.dve.petclinic.petsManagement.creation;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetCreationService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetCreationModelMapper<Pet, PetCreationModel> modelMapper;

    public void create(PetCreationModel creationModel, AuthenticatedUser user) {
        Owner owner = fetchOwnerByUserId(user.getUserId());
        Pet pet = modelMapper.mapToEntity(creationModel);
        pet.setOwner(owner);
        petRepository.save(pet);
    }

    private Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("not found owner!"));
    }
}
