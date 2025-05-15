package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.Transaction;
import com.project.PayMyBuddy.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findBySenderId(Long senderId) {
        return transactionRepository.findBySenderId(senderId);
    }

}
