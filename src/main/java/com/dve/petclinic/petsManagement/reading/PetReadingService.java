package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetReadingService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetResponseModelMapper<PetResponseModel, Pet> modelMapper;

    public List<PetResponseModel> findAllPetsByOwner(Pageable pageable, AuthenticatedUser user) {
        Owner owner = fetchOwnerByUserId(user.getUserId());

        return petRepository.findAllByOwner(pageable, owner).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public PetResponseModel findPet(Long petId, AuthenticatedUser user) {
        Owner owner = fetchOwnerByUserId(user.getUserId());
        return modelMapper.mapToModel(fetchByIdAndOwner(petId, owner));
    }

    private Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found owner!"));
    }

    private Pet fetchByIdAndOwner(Long petId, Owner owner) {
        return petRepository.findByIdAndOwner(petId, owner)
                .orElseThrow(() -> new NotFoundException("Not found pet!"));
    }
}
