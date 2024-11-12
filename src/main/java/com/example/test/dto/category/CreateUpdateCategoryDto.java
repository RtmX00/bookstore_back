package com.example.test.dto.category;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUpdateCategoryDto {
    @ApiModelProperty(required = true)
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private String name;
}
