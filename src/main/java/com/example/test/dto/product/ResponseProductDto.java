package com.example.test.dto.product;

import com.example.test.dto.category.ResponseCategoryDto;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResponseProductDto {
    private UUID id;
    private String name;
    private long price;

    private String description;
    private String nameAuthor;
    private String publicationDate;
    private Boolean condition;
    private ResponseCategoryDto category;
    private String image;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
