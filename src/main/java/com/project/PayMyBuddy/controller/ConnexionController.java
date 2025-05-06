package com.project.PayMyBuddy.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnexionController {

    @GetMapping({"/connexion", "/"})
    public String afficherConnexion(Authentication auth) {
        if ((auth != null) && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/transfert"; // renvoie si déjà connecté sur la page /transfert.html
        }
        return "connexion"; // correspond à templates/connexion.html
    }

}
