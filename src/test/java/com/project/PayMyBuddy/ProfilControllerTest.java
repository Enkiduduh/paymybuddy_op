package com.project.PayMyBuddy;

import com.project.PayMyBuddy.controller.ProfilController;
import com.project.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfilController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProfilController profilController;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;

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
    public void testAfficherProfil() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profil")
                .with(user("joe@test.fr").roles("USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
