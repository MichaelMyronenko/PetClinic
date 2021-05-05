package com.dve.petclinic.petsManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.dve.petclinic.petsManagement.PetProperties.DEFAULT_NUM_OF_PAGE;
import static com.dve.petclinic.petsManagement.PetProperties.DEFAULT_PAGE_SIZE;
import static com.dve.petclinic.petsManagement.PetProvider.getTestPets;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetRestController.class)
@Import(PetRestController.class)
@ActiveProfiles("test")
class PetRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    void getPets() throws Exception {
        when(petService.getPets(PageRequest.of(DEFAULT_NUM_OF_PAGE, DEFAULT_PAGE_SIZE))).thenReturn(getTestPets(DEFAULT_PAGE_SIZE));

        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

//    @Configuration
//    @EnableWebSecurity
//    static class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .csrf().disable()
//                    .authorizeRequests().anyRequest().anonymous();
//        }
//    }
}