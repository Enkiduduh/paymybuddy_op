package com.project.PayMyBuddy;


import com.project.PayMyBuddy.controller.ProfilController;
import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.ProfilService;
import com.project.PayMyBuddy.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ProfilServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfilService profilService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

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
    void modifyProfil() {
        // GIVEN
        //1. On charge l'utilisateur via son id
        User user = profilService.getProfileById(42L);


        //2. On modifie l'username
        user.setUsername("jack");
        user.setEmail("jack@jack.fr");

        // WHEN
        //3. On sauvegarde la modification
        userRepository.save(user);

        // THEN
        //3. On vérifie si la modification a bien été sauvegardée
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User saved = captor.getValue();
        assertEquals("jack", saved.getUsername());
        assertEquals("jack@jack.fr", saved.getEmail());
    }

}
