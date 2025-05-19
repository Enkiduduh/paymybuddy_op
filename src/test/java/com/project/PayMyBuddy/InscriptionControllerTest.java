package com.project.PayMyBuddy;


import com.project.PayMyBuddy.controller.InscriptionController;

import com.project.PayMyBuddy.service.InscriptionService;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(
        controllers = InscriptionController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)   // désactive CSRF, auth, etc.
public class InscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private InscriptionService inscriptionService;

    @Test
    void postInscription_redirectsToConnexionOnSuccess() throws Exception {
        mockMvc.perform(post("/inscription")
                        .param("username", "samy")
                        .param("email", "samy@test.fr")
                        .param("password", "secret")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/connexion?success"));

        // Vérifie que la service a bien été appelé
        verify(inscriptionService)
                .register("samy", "samy@test.fr", "secret");
    }

    @Test
    void getInscription_authenticatedRedirectsToTransfert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inscription")
                        .with(user("joe@test.fr").roles("USER")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/transfert"));
    }

}
