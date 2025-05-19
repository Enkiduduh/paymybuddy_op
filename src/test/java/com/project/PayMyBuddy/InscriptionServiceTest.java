package com.project.PayMyBuddy;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.InscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InscriptionServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private InscriptionService inscriptionService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void createUser_savesEncodedPassword() {
        //1. given
        String raw = "samy";
        String encoded = "encoded-samy";
        when(passwordEncoder.encode(raw)).thenReturn(encoded);

        //2. when
        inscriptionService.register("samy", "samy@test.fr", raw);

        //3. then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User saved = captor.getValue();
        assertEquals("samy", saved.getUsername());
        assertEquals("samy@test.fr", saved.getEmail());
        assertEquals(encoded, saved.getPassword());

    }
}

