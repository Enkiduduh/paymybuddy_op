package com.project.PayMyBuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_connection")
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "connection_id")
    private Long connectionId;
}
