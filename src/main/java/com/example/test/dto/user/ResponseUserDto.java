package com.example.test.dto.user;

import com.example.test.enums.UserRole;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseUserDto {
    private UUID id;
    private String fullname;
    private String username;
    private Timestamp createAt;
    private String beas64String;
    private String email;
    private String address;
    private Long phoneNumber;
    private UserRole userRole;
    private Timestamp  updatedAt;

}
