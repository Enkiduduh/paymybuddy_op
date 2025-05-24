package com.project.PayMyBuddy.repository;

import com.project.PayMyBuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Renvoie toutes les transactions pour l'utilisateur d'id senderId.
     */
    List<Transaction> findBySenderId(@Param("senderId") Long senderId);

}
