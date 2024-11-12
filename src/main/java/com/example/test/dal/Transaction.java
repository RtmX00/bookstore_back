package com.example.test.dal;

import com.example.test.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Entity
@Table(name = "td_Transaction")
@Data
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders orders;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users user;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Column
    private int trackingNumber;
}
