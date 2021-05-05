package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.creation.PetCreationService;
import com.dve.petclinic.petsManagement.delete.PetDeleteService;
import com.dve.petclinic.petsManagement.reading.PetReadingService;
import com.dve.petclinic.petsManagement.reading.PetResponseModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.petsManagement.update.PetUpdateService;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetCreationService petCreationService;
    private final PetReadingService petReadingService;
    private final PetDeleteService petDeleteService;
    private final PetUpdateService petUpdateService;

    public PetService(PetCreationService petCreationService, PetReadingService petReadingService, PetDeleteService petDeleteService, PetUpdateService petUpdateService) {
        this.petCreationService = petCreationService;
        this.petReadingService = petReadingService;
        this.petDeleteService = petDeleteService;
        this.petUpdateService = petUpdateService;
    }

    public void addPet(PetCreationModel creationModel) {
        petCreationService.create(creationModel);
    }

    public List<PetResponseModel> getPets(Pageable pageable) {
        return petReadingService.findAllPetsByOwner(pageable);
    }

    public PetResponseModel getPet(Long petId) {
        return petReadingService.findPet(petId);
    }

    public void deletePet(Long petId) {
        petDeleteService.delete(petId);
    }

    public void updatePet(Long petId, PetUpdateModel petUpdateModel) {
        petUpdateService.update(petId, petUpdateModel);
    }
}
