package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.reading.PetResponseModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetRestController {

    private final PetService petService;
    private final CurrentUserService currentUserService;

    public PetRestController(PetService petService, CurrentUserService currentUserService) {
        this.petService = petService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public ResponseEntity<List<PetResponseModel>> getPets() {
        return new ResponseEntity<>(petService.getPets(currentUserService.getCurrentUser()), HttpStatus.OK);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseModel> getPet(@PathVariable Long petId) {
        return new ResponseEntity<>(petService.getPet(petId), HttpStatus.OK);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> editPet(@PathVariable Long petId,
                                        @Valid @RequestBody PetUpdateModel updateModel) {
        petService.updatePet(petId, updateModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
