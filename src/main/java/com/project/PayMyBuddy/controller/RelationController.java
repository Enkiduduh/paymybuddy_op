package com.project.PayMyBuddy.controller;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.service.RelationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RelationController {
    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @GetMapping("/relation")
    public String afficherRelation(Authentication auth, Model model) {

        //1. Récupération du user connecté
        User currentUser = (User) auth.getPrincipal();

        //2. Ajouter user au modèle
        model.addAttribute("user", currentUser);

        return "relation"; // correspond à templates/relation.html
    }

    @PostMapping("/relation")
    public String addRelation(
            @RequestParam String email,
            Authentication auth
    ) {
        try {
            User from = (User) auth.getPrincipal();
            relationService.addRelation(from.getId(), email);
            return "redirect:/relation?success";
        } catch (EntityNotFoundException e) {
            return "redirect:/relation?error";
        }
    }
}
