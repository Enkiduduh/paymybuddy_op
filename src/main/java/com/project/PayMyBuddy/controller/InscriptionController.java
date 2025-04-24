package com.project.PayMyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InscriptionController {

    @GetMapping("/inscription")
    public String afficherInscription() {
        return "inscription"; // correspond à templates/inscription.html
    }
}
