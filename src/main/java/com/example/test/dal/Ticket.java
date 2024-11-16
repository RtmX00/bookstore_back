package com.example.test.dal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Entity
@Table(name = "Ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Users users;

    private String contents;
}
