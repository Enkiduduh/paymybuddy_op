package com.project.PayMyBuddy.controller;


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

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @GetMapping("/profil")
    public String afficherProfil(Authentication auth, Model model) {

        // Récupère l’utilisateur stocké dans le SecurityContext
        User currentUser = (User) auth.getPrincipal();
        model.addAttribute("user", currentUser);
        return "profil"; // correspond à templates/profil.html
    }

    @PostMapping("/profil")
    public String modifyProfil(
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false)String newPassword,
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
