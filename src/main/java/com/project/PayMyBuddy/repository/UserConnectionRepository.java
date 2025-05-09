package com.project.PayMyBuddy.repository;

import com.project.PayMyBuddy.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
    /**
     * Renvoie toutes les UserConnection pour l'utilisateur d'id userId.
     */
   List<UserConnection> findByUserId(Long userId);
}
