package com.example.test.dal;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "News")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String content;
    private Timestamp createAt;
    private Timestamp updatedAt;
}
