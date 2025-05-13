package com.project.PayMyBuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_connection")
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connection_id")
    private User connection;
}
