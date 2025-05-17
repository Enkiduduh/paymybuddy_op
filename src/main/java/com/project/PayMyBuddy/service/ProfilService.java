package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfilService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfilService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void modifyProfil(
            Long id,
            String newUsername,
            String newEmail,
            String newRawPassword
    ) {
        //1. Charger les données de l'utilisateur
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        //2. Modification des champs si nécessaire
        // Met à jour le username
        if (newUsername != null
                && !newUsername.isBlank()
                && !newUsername.equals(user.getUsername())) {
            user.setUsername(newUsername);
        }

        // Met à jour l'email
        if (newEmail != null
                && !newEmail.isBlank()
                && !newEmail.equals(user.getEmail())) {
            user.setEmail(newEmail);
        }

        // Met à jour le mot de passe (encodé)
        if (newRawPassword != null
                && !newRawPassword.isBlank()) {
            user.setPassword(passwordEncoder.encode(newRawPassword));
        }

        //3. Sauvegarde de la modification
        userRepository.save(user);
    }
}
