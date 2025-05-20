package com.project.PayMyBuddy;

import com.project.PayMyBuddy.controller.ProfilController;
import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.ProfilService;
import com.project.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProfilController.class)
@AutoConfigureMockMvc
public class ProfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProfilService profilService;

    @MockBean
    private UserRepository userRepository;

    private com.project.PayMyBuddy.model.User newUser;

    @BeforeEach
    void setup() {
        newUser = new com.project.PayMyBuddy.model.User();
        newUser.setId(42L);
        newUser.setUsername("joe");
        newUser.setEmail("joe@joe.fr");
        newUser.setPassword("joe");

        when(profilService.getProfileById(42L))
                .thenReturn(newUser);
    }

    @Test
    void testAfficherProfil() throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                newUser,                    // ici, c'est l'objet que vous avez stubé
                newUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/profil")
                        .with(authentication(auth)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void modifyProfil() throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                newUser, newUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/profil")
                        .with(authentication(auth))
                        .with(csrf())
                .param("newUsername", "jojo")
                .param("newEmail",    "jojo@jojo.fr")
                .param("newPassword", "jojo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profil?success"));
        // Verify
        verify(profilService).modifyProfil(42L, "jojo", "jojo@jojo.fr", "jojo");
    }

}
