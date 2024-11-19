package com.example.test.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CreateUpdateProductDto {
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private String name;
    private long price;
    private int percentage;
    private String description;
    @NotNull
    @Length(min = 2, message = "please enter NameOfTheAuthor  not valid")
    private String nameAuthor;
    @NotNull
    @Length(min = 2, message = "please enter publicationDate not valid")
    private String publicationDate;
    @NotNull
    private UUID categoryId;

    private String image;


}
