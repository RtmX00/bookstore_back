package com.example.test.dto.user;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateUserDto {
    @ApiModelProperty(required = true)
    @Length(min = 2 , message = "please enter user name not valid")
    private String fullname;
    @NotNull
    @Length(min = 2 , message = "please enter user name not valid")
    private String username;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private String password;
    private String email;
    private Long phoneNumber;
    private String address;
    private String beas64String;


}
