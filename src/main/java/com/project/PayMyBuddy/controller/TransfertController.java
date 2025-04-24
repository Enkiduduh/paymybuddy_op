package com.project.PayMyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransfertController {

    @GetMapping("/transfert")
    public String afficherTransfert() {
        return "transfert"; // correspond à templates/transfert.html
    }
}
