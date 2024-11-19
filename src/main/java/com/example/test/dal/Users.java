package com.example.test.dal;


import com.example.test.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.ws.soap.addressing.server.annotation.Address;

import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
public class Users extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Long phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String address;

}
