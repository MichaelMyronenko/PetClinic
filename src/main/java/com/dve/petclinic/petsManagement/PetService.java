package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.creation.PetCreationService;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private final PetCreationService petCreationService;

    public void addPet(PetCreationModel creationModel, AuthenticatedUser authenticatedUser) {
        petCreationService.create(creationModel, authenticatedUser);
    }
}
