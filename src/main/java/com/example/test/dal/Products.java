package com.example.test.dal;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class Products extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private long price;
    private int percentage;
    private String nameAuthor;
    private String publicationDate;
    private long totalPrice;
    @ManyToOne
    private Category category;
    private  Boolean condition;
    private String description;



}