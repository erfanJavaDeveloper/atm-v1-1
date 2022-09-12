package com.payeshgaran.model.account;

import com.payeshgaran.entity.Account;
import com.payeshgaran.entity.Locked;
import com.payeshgaran.entity.TypeOfAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Data
public class AccountInDto {

    @ApiModelProperty(required = true)
//    @Validated(value = "^([0-9]{3,5})$")
    @Pattern(regexp = "^([0-9]{3,5})$")
    private String pin;

    @ApiModelProperty(required = true)
    private BigInteger balance;

    @ApiModelProperty(required = true)
    private TypeOfAccount type = TypeOfAccount.LOANS;

    @ApiModelProperty(required = true)
    private Locked locked = Locked.ENABLE;

    @ApiModelProperty(required = true)
    private int incorrectAttempts = 0;

    public Account converterDtoToEntity(AccountInDto accountInDto) {
        Account account = new Account();
        account.setBalance(accountInDto.getBalance());
        account.setLocked(accountInDto.getLocked());
        account.setPin(accountInDto.getPin());
        account.setIncorrectAttempts(accountInDto.getIncorrectAttempts());
        account.setType(accountInDto.getType());
        return account;
    }

}
