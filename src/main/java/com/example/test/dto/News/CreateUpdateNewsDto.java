package com.example.test.dto.News;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CreateUpdateNewsDto {
    private String title;
    private String content;
}
