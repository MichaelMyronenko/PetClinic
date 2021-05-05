package com.dve.petclinic.petsManagement;

import com.dve.petclinic.petsManagement.creation.PetCreationModel;
import com.dve.petclinic.petsManagement.update.PetUpdateModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String pet(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("petList", petService.getPets(pageable));
        return "pets";
    }

    @PostMapping
    public String addPet(@Valid @ModelAttribute PetCreationModel petCreationModel) {
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
