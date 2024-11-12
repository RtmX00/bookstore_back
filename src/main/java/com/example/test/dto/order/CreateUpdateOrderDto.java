package com.example.test.dto.order;

import com.example.test.enums.OrderStatus;
import com.example.test.dal.Users;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUpdateOrderDto {
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private Users users;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private String name;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private  long number;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private double price;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private OrderStatus status;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    private long totalPrice;

}
