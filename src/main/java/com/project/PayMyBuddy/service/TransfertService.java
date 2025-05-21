package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.Transaction;
import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.repository.TransactionRepository;
import com.project.PayMyBuddy.repository.UserConnectionRepository;
import com.project.PayMyBuddy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransfertService {
    private final UserConnectionRepository userConnectionRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    public TransfertService(
            UserConnectionRepository userConnectionRepository,
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        this.userConnectionRepository = userConnectionRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<UserConnection> findByUserId(Long userId) {
        return userConnectionRepository.findByUserId(userId);
    }

    @Transactional
    public Transaction executeTransfert(
            Long senderId,
            Long receiverId,
            String description,
            Double amount
    ) {

        //1. Charger les utilisateurs senderId et receiverId
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new EntityNotFoundException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver not found"));

        //2. Créer la transaction
        Transaction trans = new Transaction();
        trans.setSender(sender);
        trans.setReceiver(receiver);
        trans.setDescription(description);
        trans.setAmount(amount);

        //3. Sauvegarder l'objet trans crée
        transactionRepository.save(trans);
        return trans;
    }
}
