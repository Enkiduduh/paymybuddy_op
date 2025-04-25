package com.project.PayMyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String afficherProfil() {
        return "profil"; // // correspond à templates/propfil.html
    }
}
