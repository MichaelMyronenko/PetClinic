package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final CurrentUserService currentUserService;

    public PetController(PetService petService, CurrentUserService currentUserService) {
        this.petService = petService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public String pet(Model model) {
        model.addAttribute("petList", petService.getPets(currentUserService.getCurrentUser()));
        return "pets";
    }

    @PostMapping
    public String addPet(@Valid @ModelAttribute PetCreationModel petCreationModel,
                         Model model) {
        petService.addPet(petCreationModel);
        return "redirect:/pets";
    }

    @PostMapping("/delete/{petId}")
    public String deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);

        return "redirect:/pets";
    }

    @PostMapping("/edit/{petId}")
    public String editPet(@PathVariable Long petId,
                          @Valid @ModelAttribute PetUpdateModel petUpdateModel) {
        petService.updatePet(petId, petUpdateModel);

        return "redirect:/pets";
    }
}
