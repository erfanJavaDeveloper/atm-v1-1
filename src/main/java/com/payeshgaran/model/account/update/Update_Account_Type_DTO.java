package com.payeshgaran.model.account.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Update_Account_Type_DTO {
    @ApiModelProperty(required = true)
    private Boolean type;
}
