package com.example.test.dto.Transaction;

import com.example.test.enums.TransactionStatus;
import com.example.test.dal.Orders;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreatInsertTransactionDto {

    @ApiModelProperty(required = true)
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private Orders orders;
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    @ApiModelProperty(required = true)
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private TransactionStatus status;
    @ApiModelProperty(required = true)
    @NotNull
    @Length(min = 2, message = "please enter user name not valid")
    private int trackingNumber;
}
