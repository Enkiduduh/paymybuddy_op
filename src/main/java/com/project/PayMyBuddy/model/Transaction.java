package com.project.PayMyBuddy.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sender_id;
    private Long receiver_id;
    private String description;
    private Double amount;
}

