package com.payeshgaran.model.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransactionFindInDtoNew {

    @ApiModelProperty
    private String accountNumber;
}
