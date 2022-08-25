package com.payeshgaran.dto.account.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Update_Account_Locked_DTO {
    @ApiModelProperty(required = true)
    private Boolean locked;
}
