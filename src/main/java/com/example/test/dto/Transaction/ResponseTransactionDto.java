package com.example.test.dto.Transaction;

import com.example.test.enums.TransactionStatus;
import com.example.test.dal.Orders;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseTransactionDto {
    private UUID id ;
    private Orders orders;
    private TransactionStatus status;
    private int trackingNumber;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
