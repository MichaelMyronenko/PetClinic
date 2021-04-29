package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetReadingService {

    private final PetResponseModelMapper modelMapper = new PetResponseModelMapper();
    private final PetRepository petRepository;
    private final OwnerFetcher ownerFetcher;
    private final PetFetcher petFetcher;
    private final CurrentUserService currentUserService;

    public PetReadingService(PetRepository petRepository, OwnerFetcher ownerFetcher, PetFetcher petFetcher, CurrentUserService currentUserService) {
        this.petRepository = petRepository;
        this.ownerFetcher = ownerFetcher;
        this.petFetcher = petFetcher;
        this.currentUserService = currentUserService;
    }

    public List<PetResponseModel> findAllPetsByOwner(Pageable pageable, AuthenticatedUser user) {
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());

        return petRepository.findAllByOwner(pageable, owner).stream()
                .map(modelMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public PetResponseModel findPet(Long petId) {
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());

        return modelMapper.mapToModel(petFetcher.fetchByIdAndOwner(petId, owner));
    }
}
