package com.project.PayMyBuddy.controller;

import com.project.PayMyBuddy.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InscriptionController {

    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/inscription")
    public String afficherInscription() {
        return "inscription"; // correspond à templates/inscription.html
    }

    @PostMapping("/inscription")
    public String createUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password) {
        inscriptionService.register(username, email, password);
        return "redirect:/connexion?inscriptionSuccess";
    }


}
