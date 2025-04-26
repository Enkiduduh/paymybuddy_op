package com.project.PayMyBuddy.repository;

import com.project.PayMyBuddy.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
//    List<UserConnection> findByUser_id(List<Long> users);
}
