package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.repository.UserConnectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransfertService {
    private final UserConnectionRepository userConnectionRepository;

    public TransfertService(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    public List<UserConnection> findByUserId(Long userId) {
        return userConnectionRepository.findByUserId(userId);
    }


}
