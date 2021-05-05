package com.dve.petclinic.petsManagement.reading;

import com.dve.petclinic.security.userDetails.WithCustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithCustomUserDetails
@AutoConfigureMockMvc
public class PetReadingTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPets_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getPetsNonexistencePageOfPets_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/pets?page=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getPetById_thenReturnOk() throws Exception {
        int petId = 1;
        mockMvc.perform(get("/api/pets/" + petId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.petId", is(petId)));
    }

    @Test
    void getNonexistentPetById_thenReturnNotFound() throws Exception {
        int petId = 100;
        mockMvc.perform(get("/api/pets/" + petId))
                .andExpect(status().isNotFound());
    }
}