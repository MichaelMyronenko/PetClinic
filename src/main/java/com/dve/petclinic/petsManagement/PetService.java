package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.creation.PetCreationService;
import com.dve.petclinic.petsManagement.delete.PetDeleteService;
import com.dve.petclinic.petsManagement.reading.PetReadingService;
import com.dve.petclinic.petsManagement.reading.PetResponseModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.petsManagement.update.PetUpdateService;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {
    private final PetCreationService petCreationService;
    private final PetReadingService petReadingService;
    private final PetDeleteService petDeleteService;
    private final PetUpdateService petUpdateService;

    public void addPet(PetCreationModel creationModel, AuthenticatedUser authenticatedUser) {
        petCreationService.create(creationModel, authenticatedUser);
    }

    public List<PetResponseModel> getPets(AuthenticatedUser user) {
        return petReadingService.findAllPetsByOwner(PageRequest.of(0, 8), user);
    }

    public PetResponseModel getPet(Long petId, AuthenticatedUser user) {
        return petReadingService.findPet(petId, user);
    }

    public void deletePet(Long petId, AuthenticatedUser user) {
        petDeleteService.delete(petId, user);
    }

    public void updatePet(Long petId, AuthenticatedUser user, PetUpdateModel petUpdateModel) {
        petUpdateService.update(petId, petUpdateModel, user);
    }
}