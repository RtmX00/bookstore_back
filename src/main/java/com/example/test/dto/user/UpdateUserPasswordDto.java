package com.example.test.dto.user;

import com.example.test.enums.UserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserPasswordDto {
    @ApiModelProperty(required = true)
    private String oldPassword;
    @ApiModelProperty(required = true)
    private String newPassword;
    @ApiModelProperty(required = true)
    private UserRole userRole;
    private String email;
    private Long phoneNumber;

}
