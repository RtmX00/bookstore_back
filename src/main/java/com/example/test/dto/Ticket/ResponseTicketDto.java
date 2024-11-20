package com.example.test.dto.Ticket;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseTicketDto {
    private UUID id;
    private UUID fromId;
    private UUID toId;
    private String content;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
