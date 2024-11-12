package com.example.test.dto.category;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseCategoryDto {
    private UUID id;
    private String name;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
