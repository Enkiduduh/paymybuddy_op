package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.model.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InscriptionService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String email, String rawPassword) {
        System.out.println(">> INSCRIPTION SERVICE appelé avec : "
                + username + " / " + email + " / " + rawPassword);
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(u);
    }
}
