package com.project.PayMyBuddy.controller;

import com.project.PayMyBuddy.model.Transaction;
import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.service.TransactionService;
import com.project.PayMyBuddy.service.TransfertService;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import com.project.PayMyBuddy.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransfertController {

    private final TransfertService transfertService;
    private final TransactionService transactionService;

    public TransfertController(TransfertService transfertService, TransactionService transactionService) {
        this.transfertService = transfertService;
        this.transactionService = transactionService;
    }

    @GetMapping("/transfert")
    public String afficherTransfert(Authentication auth, Model model) {

        // 1. Récupère l’utilisateur connecté
        User currentUser = (User) auth.getPrincipal();

        // 2. Charge ses relations (UserConnection)
        List<UserConnection> relations =
                transfertService.findByUserId(currentUser.getId());

        // 3. Charge ses transactions (Transaction)
        List<Transaction> transactions =
                transactionService.findBySenderId(currentUser.getId());

        // 4. Ajoute user + relations + transactions au modèle
        model.addAttribute("user", currentUser);
        model.addAttribute("relations", relations);
        model.addAttribute("transactions", transactions);

        return "transfert"; // correspond à templates/transfert.html
    }

    @PostMapping("/transfert")
    public String envoyerTransfert(
            @RequestParam Long connectionId,
            @RequestParam String description,
            @RequestParam Double amount,
            Authentication auth) {

        User from = (User) auth.getPrincipal();
        transfertService.executeTransfert(from.getId(), connectionId, description, amount);
        return "redirect:/transfert?success";
    }
}
