package com.payeshgaran.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.payeshgaran.entity.Account;
import com.payeshgaran.entity.Locked;
import com.payeshgaran.entity.TypeOfAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class AccountInDto {
    @ApiModelProperty(required = true)
    private String accountNumber;

    @ApiModelProperty(required = true)
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
        account.setAccountNumber(accountInDto.getAccountNumber());
        account.setBalance(accountInDto.getBalance());
        account.setLocked(accountInDto.getLocked());
        account.setPin(accountInDto.getPin());
        account.setIncorrectAttempts(accountInDto.getIncorrectAttempts());
        account.setType(accountInDto.getType());
        return account;
    }
}
