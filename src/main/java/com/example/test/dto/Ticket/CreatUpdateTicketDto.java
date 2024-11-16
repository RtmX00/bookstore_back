
package com.example.test.dto.Ticket;

import com.example.test.dal.Ticket;
import com.example.test.dal.Users;
import lombok.Data;

import java.util.UUID;

@Data
public class CreatUpdateTicketDto {
    private UUID id;
    private Users users;
    private String contents;
}
