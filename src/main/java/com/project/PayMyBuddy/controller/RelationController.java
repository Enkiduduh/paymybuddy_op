package com.project.PayMyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelationController {
    @GetMapping("/relation")
    public String afficherRelation() {
        return "relation"; // correspond à templates/relation.html
    }
}
