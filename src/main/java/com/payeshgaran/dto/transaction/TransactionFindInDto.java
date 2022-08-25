package com.payeshgaran.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransactionFindInDto {

    @ApiModelProperty
    String accountNumber;
}
