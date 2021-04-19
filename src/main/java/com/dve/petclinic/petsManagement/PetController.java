package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping
    public String pet(@AuthenticationPrincipal AuthenticatedUser user,
                      Model model) {
        model.addAttribute("petList", petService.getPets(user));
        return "pets";
    }

    @PostMapping
    public String addPet(@AuthenticationPrincipal AuthenticatedUser user,
                         @Valid @ModelAttribute PetCreationModel petCreationModel,
                         Model model) {
        petService.addPet(petCreationModel, user);
        return "redirect:/pets";
    }

    @PostMapping("/delete/{petId}")
    public String deletePet(@PathVariable Long petId,
                            @AuthenticationPrincipal AuthenticatedUser user) {
        petService.deletePet(petId, user);

        return "redirect:/pets";
    }

    @PostMapping("/edit/{petId}")
    public String editPet(@PathVariable Long petId,
                          @AuthenticationPrincipal AuthenticatedUser user,
                          @Valid @ModelAttribute PetUpdateModel petUpdateModel) {
        petService.updatePet(petId, user, petUpdateModel);

        return "redirect:/pets";
    }
}
