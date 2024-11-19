package com.example.test.dto.Ticket;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CreateUpdateTicketDto {
    @ApiModelProperty(required = true)
    @NotNull
    private UUID id;
    private String from;
    private String to;
    private String content;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
