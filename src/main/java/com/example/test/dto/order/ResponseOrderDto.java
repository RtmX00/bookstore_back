package com.example.test.dto.order;

import com.example.test.dto.user.ResponseUserDto;
import com.example.test.enums.OrderStatus;
import com.example.test.dal.Users;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseOrderDto {
    private UUID id;
    private ResponseUserDto user;
    private String name;
    private  long number;
    private double price;
    private OrderStatus status;
    private long totalPrice;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
