package com.payeshgaran.validation;

import com.payeshgaran.model.transaction.TransactionInDto;
import com.payeshgaran.service.TransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class TransactionValidation {
    private TransactionService transactionService;

    public TransactionValidation(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public String currentAccount() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username ;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public Boolean checkCurrentUser(TransactionInDto transactionInDto) {
        String accountNumber =currentAccount();
        if (transactionInDto.getAccountNumberSender().equals(accountNumber)) {
            return true;
        }
        return false;
    }
}
