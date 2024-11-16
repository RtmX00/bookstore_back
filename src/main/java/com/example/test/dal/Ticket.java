package com.example.test.dal;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Table(name = "Ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID From;

    private UUID To;

    private String content;

    private Timestamp createAt;
    private Timestamp  updatedAt;
}
