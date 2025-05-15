package com.project.PayMyBuddy.controller;

import com.project.PayMyBuddy.repository.TransactionRepository;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



}
