package com.project.PayMyBuddy;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.ProfilService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProfilServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProfilService profilService;

    private User existing;

    @BeforeEach
    void setup() {
        existing = new User();
        existing.setId(42L);
        existing.setUsername("oldUser");
        existing.setEmail("old@domain.com");
        existing.setPassword("encodedOld");

        given(userRepository.findById(42L))
                .willReturn(Optional.of(existing));
    }

    @Test
    void modifyProfil_updatesOnlyNonNullFieldsAndEncodesPassword() {
        // GIVEN
        String newUsername = "newUser";
        String newEmail    = "new@domain.com";
        String rawPassword = "plainPass";
        String encodedPass = "encodedPass";

        given(passwordEncoder.encode(rawPassword)).willReturn(encodedPass);

        // WHEN
        profilService.modifyProfil(42L, newUsername, newEmail, rawPassword);

        // THEN
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User saved = captor.getValue();

        assertThat(saved.getId()).isEqualTo(42L);
        assertThat(saved.getUsername()).isEqualTo(newUsername);
        assertThat(saved.getEmail()).isEqualTo(newEmail);
        assertThat(saved.getPassword()).isEqualTo(encodedPass);
    }

    @Test
    void modifyProfil_whenUserNotFound_throwsException() {
        // GIVEN
        given(userRepository.findById(99L))
                .willReturn(Optional.empty());

        // THEN
        assertThrows(EntityNotFoundException.class, () ->
                profilService.modifyProfil(99L, "x", "x@x", "x")
        );
    }
}
