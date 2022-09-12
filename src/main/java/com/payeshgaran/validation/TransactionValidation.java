package com.payeshgaran.validation;

import com.payeshgaran.model.transaction.TransactionInDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class TransactionValidation {

    /** this method can find current account
     *
     * @return accountNumber of current account
     */
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
