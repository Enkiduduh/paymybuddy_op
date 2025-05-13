package com.project.PayMyBuddy.controller;


import org.springframework.ui.Model;
import com.project.PayMyBuddy.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String afficherProfil(Authentication auth, Model model) {

        // Récupère l’utilisateur stocké dans le SecurityContext
        User currentUser = (User) auth.getPrincipal();
        model.addAttribute("user", currentUser);
        return "profil"; // correspond à templates/propfil.html
    }
}
