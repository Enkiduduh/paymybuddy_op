package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.repository.UserConnectionRepository;
import com.project.PayMyBuddy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RelationService {
    private final UserRepository userRepository;
    private final UserConnectionRepository userConnectionRepository;

    public RelationService(UserRepository userRepository, UserConnectionRepository userConnectionRepository) {
        this.userRepository = userRepository;
        this.userConnectionRepository = userConnectionRepository;
    }

    @Transactional
    public void addRelation(
            Long userId,
            String email
    ) {
        //1. Charger les utilisateurs userId et connectionId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User connection = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Connection not found"));

        //2. Créer la relation entre user et connection
        UserConnection con = new UserConnection();
        con.setUser(user);
        con.setConnection(connection);

        //3. Sauvegarde de  l'objet con
        userConnectionRepository.save(con);
    }

    ;

}
