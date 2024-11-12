package com.example.test.dto.user;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    @NotNull
    @Length(min = 2 , message = "please enter user name not valid")
    private String username;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private String password;


}
