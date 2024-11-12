package com.example.test.dal;

import com.example.test.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name= "Orders")
@Data
public class Orders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;
    private String name;
    private  long number;
    private double price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private long totalPrice;
}
