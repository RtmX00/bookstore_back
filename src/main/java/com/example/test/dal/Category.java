package com.example.test.dal;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "category")
@Data
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private  Boolean condition;
}