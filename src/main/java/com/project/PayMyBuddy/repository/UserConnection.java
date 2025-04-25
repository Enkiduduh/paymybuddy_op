package com.project.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnection extends JpaRepository<UserConnection, Long> {
    List<UserConnection> findByUser_id(List<Long> users);
}
