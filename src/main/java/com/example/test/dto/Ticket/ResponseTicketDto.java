package com.example.test.dto.Ticket;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseTicketDto {
    private UUID id;
    private String from;
    private String to;
    private String content;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
