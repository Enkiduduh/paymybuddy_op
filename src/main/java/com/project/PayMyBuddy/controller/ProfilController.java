package com.project.PayMyBuddy.controller;


import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.ProfilService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import com.project.PayMyBuddy.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfilController {
    private final ProfilService profilService;
    private final UserRepository userRepository;

    public ProfilController(ProfilService profilService, UserRepository userRepository) {
        this.profilService = profilService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profil")
    public String afficherProfil(Authentication auth, Model model) {

        //1. on récupère juste l'ID du principal stocké dans le SecurityContext
        User principal = (User) auth.getPrincipal();
        Long userId = principal.getId();

        //2. Récupération à jour de l'utilisation depuis la BDD
        User fresh = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        model.addAttribute("user", fresh);
        return "profil"; // correspond à templates/profil.html
    }

    @PostMapping("/profil")
    public String modifyProfil(
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false) String newPassword,
            Authentication auth) {
        try {
            User from = (User) auth.getPrincipal();
            profilService.modifyProfil(from.getId(), newUsername, newEmail, newPassword);
            return "redirect:/profil?success";
        } catch (EntityNotFoundException e) {
            return "redirect:/profil?error";
        }
    }

    ;
}
