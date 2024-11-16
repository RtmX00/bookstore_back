package com.example.test.dto.Ticket;

import com.example.test.dal.Users;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseTicketDto {
    private Users users;
    private String contents;
}
