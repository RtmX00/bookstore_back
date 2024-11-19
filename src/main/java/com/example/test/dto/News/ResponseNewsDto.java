package com.example.test.dto.News;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseNewsDto {
    private UUID id;
    private String title;
    private String content;
    private Timestamp createAt;
    private Timestamp updatedAt;

}
