package com.project.PayMyBuddy.controller;

import org.springframework.ui.Model;
import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.repository.UserConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserConnectionController {

    private final UserConnectionRepository userConnectionRepository;

    public UserConnectionController(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    @GetMapping("/user-connections")
    public String listUserConnections(Model model) {
        List<UserConnection> connections = userConnectionRepository.findAll();
        model.addAttribute("connections", connections);
        return "user_connections"; // correspond à user_connections.html dans templates/
    }
}
