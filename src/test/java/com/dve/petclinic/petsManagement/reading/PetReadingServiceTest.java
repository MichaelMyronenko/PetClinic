package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import com.dve.petclinic.entities.pet.PetRepository;
import com.dve.petclinic.owner.OwnerProvider;
import com.dve.petclinic.ownersManagement.OwnerFetcher;
import com.dve.petclinic.petsManagement.PetFetcher;
import com.dve.petclinic.petsManagement.PetProperties;
import com.dve.petclinic.petsManagement.PetProvider;
import com.dve.petclinic.security.AuthenticatedUser;
import com.dve.petclinic.security.CurrentUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.when;
import static com.dve.petclinic.security.AuthenticatedUserProvider.*;
import static com.dve.petclinic.petsManagement.PetProperties.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PetReadingServiceTest {

    @InjectMocks
    PetReadingService petReadingService;

    @Mock
    CurrentUserService currentUserService;

    @Mock
    PetFetcher petFetcher;

    @Mock
    PetRepository petRepository;

    @Mock
    OwnerFetcher ownerFetcher;

    @Test
    void getPets() {
        Pageable pageable = PageRequest.of(DEFAULT_NUM_OF_PAGE, DEFAULT_PAGE_SIZE);
        AuthenticatedUser user = getTestAuthenticatedOwner();
        Owner testOwner = OwnerProvider.getOwner(1L);
        Page<Pet> pets = new PageImpl<>(PetProvider.getTestPetEntities(DEFAULT_PAGE_SIZE));

        when(currentUserService.getCurrentUser()).thenReturn(user);
        when(ownerFetcher.fetchOwnerByUserId(user.getUserId())).thenReturn(testOwner);
        when(petRepository.findAllByOwner(pageable, testOwner))
                .thenReturn(pets);

        List<PetResponseModel> petResponseModels = petReadingService.findAllPetsByOwner(pageable);

        assertThat(petResponseModels).hasSize(DEFAULT_PAGE_SIZE);
    }
}
