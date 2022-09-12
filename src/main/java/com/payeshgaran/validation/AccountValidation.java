package com.payeshgaran.validation;

import com.payeshgaran.model.transaction.TransactionInDto;
import com.payeshgaran.service.AccountService;

import java.math.BigInteger;

public class AccountValidation {
    AccountService accountService = new AccountService() ;



    public Boolean checkAmountOfAccount(TransactionInDto transactionInDto) {
//        if (!(transactionInDto.getAccountNumberSender().isEmpty())) {
        BigInteger balance = accountService.findByAccountNumber(transactionInDto.getAccountNumberSender()).getBalance();
        int i = balance.compareTo(transactionInDto.getAmount());
        if (i > 0) {
            return true;
        }
        return false;
//        }
//        throw new NullPointerException();
    }
}
