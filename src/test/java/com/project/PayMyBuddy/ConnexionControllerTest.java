package com.project.PayMyBuddy;

import com.project.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
public class ConnexionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean              // ← Remplace le bean UserService dans le contexte
    private UserService userService;

    // ne plus @Autowired ici
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @BeforeEach
    void setup() {
        UserDetails joe = User.builder()
                .username("joe@test.fr")
                .password(passwordEncoder.encode("joe"))
                .roles("USER")   // au moins un rôle
                .build();
        when(userService.loadUserByUsername("joe@test.fr"))
                .thenReturn(joe);
    }

    @Test
    public void testAfficherConnexion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/connexion")).andExpect(status().isOk()).andDo(print());
    }


    @Test
    void loginWithValidCredentials() throws Exception {
        mockMvc.perform(formLogin("/connexion")
                        .user("joe@test.fr").password("joe")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/transfert"));
    }

    @Test
    void whenAlreadyAuthenticated_thenRedirectToTransfert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/connexion")
                        // on simule un user déjà loggé
                        .with(user("joe@test.fr").roles("USER")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/transfert"));
    }

    @Test
    @DisplayName("POST /connexion avec mauvais identifiants → redirige vers /connexion?error")
    void loginWithInvalidCredentials() throws Exception {
        // on simule un échec d'authentification
        given(userService.loadUserByUsername(anyString()))
                .willThrow(new UsernameNotFoundException("not found"));

        mockMvc.perform(post("/connexion")
                        .param("username", "inconnu@test.fr")
                        .param("password", "whatever")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/connexion?error"));
    }


}
