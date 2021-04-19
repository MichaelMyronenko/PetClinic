package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.reading.PetResponseModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@AllArgsConstructor
public class PetRestController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetResponseModel>> getPets(@AuthenticationPrincipal AuthenticatedUser user) {
        return new ResponseEntity<>(petService.getPets(user), HttpStatus.OK);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseModel> getPet(@PathVariable Long petId,
                                                   @AuthenticationPrincipal AuthenticatedUser user) {
        return new ResponseEntity<>(petService.getPet(petId, user), HttpStatus.OK);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> editPet(@PathVariable Long petId,
                                        @AuthenticationPrincipal AuthenticatedUser user,
                                        @Valid @RequestBody PetUpdateModel updateModel) {
        petService.updatePet(petId, user, updateModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId,
                                          @AuthenticationPrincipal AuthenticatedUser user) {
        petService.deletePet(petId, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
