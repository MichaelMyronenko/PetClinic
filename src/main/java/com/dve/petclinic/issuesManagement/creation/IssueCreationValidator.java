package com.dve.petclinic.issuesManagement.creation;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IssueCreationValidator implements Validator {

    private final OwnerFetcher ownerFetcher;
    private final PetFetcher petFetcher;
    private final CurrentUserService currentUserService;

    public IssueCreationValidator(OwnerFetcher ownerFetcher, PetFetcher petFetcher, CurrentUserService currentUserService) {
        this.ownerFetcher = ownerFetcher;
        this.petFetcher = petFetcher;
        this.currentUserService = currentUserService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(IssueCreationModel.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        IssueCreationModel model = (IssueCreationModel) target;
        AuthenticatedUser user = currentUserService.getCurrentUser();
        Pet pet = petFetcher.fetchPetById(model.getPetId());
        Owner owner = ownerFetcher.fetchOwnerByUserId(user.getUserId());

        if (!(owner.equals(pet.getOwner()))) {
            errors.reject("Forbidden.UserCreationService.create");
        }
    }
}
