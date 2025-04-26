package com.project.PayMyBuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UserConnection")
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "connection_id")
    private Long connectionId;
}
