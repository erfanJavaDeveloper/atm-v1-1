package com.payeshgaran.model.account.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Update_Account_Pin_DTO {
    @ApiModelProperty(required = true)
    private String pin;
}
