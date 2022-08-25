package com.payeshgaran.dto.account.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Update_AccountIncorrect_Attempts_DTO {
    @ApiModelProperty(required = true)
    private int incorrectAttempts;
}
