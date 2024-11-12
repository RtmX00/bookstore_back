package com.example.test.dal;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Products products;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
