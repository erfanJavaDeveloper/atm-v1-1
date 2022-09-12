package com.payeshgaran.service;

import com.payeshgaran.entity.TypeOfTransaction;
import com.payeshgaran.model.transaction.TransactionInDto;
import com.payeshgaran.entity.Account;
import com.payeshgaran.entity.Transaction;
import com.payeshgaran.exception.exNew.ThisAccountNumberIsNotForCurrentAccountException;
import com.payeshgaran.dao.TransactionDao;
import com.payeshgaran.validation.AccountValidation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payeshgaran.validation.TransactionValidation;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
public class TransactionService {


    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private AccountService accountService;

    private final AccountValidation accountValidation = new AccountValidation();


    private TransactionValidation transactionValidation = new TransactionValidation();

    public TransactionService(TransactionValidation transactionValidation) {
        this.transactionValidation = transactionValidation;
    }


    //todo there is two way for save
    //todo            1) true
    //todo            2) false
    //todo if false happened save but don't show to database ✨ ¯\_(ツ)_/¯
    @Transactional
    public Transaction save(TransactionInDto transactionInDto) {
        Transaction save = new Transaction();
        if (transactionValidation.checkCurrentUser(transactionInDto)) {
//            if (accountValidation.checkAmountOfAccount(transactionInDto)) {
            if (checkAmountOfAccount(transactionInDto)) {


//            save = transactionDao.saveAndFlush(TransactionInDto.convertDtoToEntity(transactionInDto));

                save = transactionDao.saveAndFlush(TransactionInDto.convertDtoToEntity(transactionInDto));
                Account sender = accountService.findByAccountNumber(save.getAccountNumberSender());
//            sender.setTransaction(List.of(save)); //todo: amirhossein
                sender.setBalance(sender.getBalance().subtract(save.getAmount()));

                Account receiver = accountService.findByAccountNumber(save.getAccountNumberReceiver());
//            receiver.setTransaction(List.of(save));
                receiver.setBalance(receiver.getBalance().add(save.getAmount()));
                // amirhossein
//            receiver.addTransaction(save);
                sender.addTransaction(save);

                save.setIsTrue(true);

                save.setType(TypeOfTransaction.DONE);
                return save;
            }
        }

        try {
            save.setType(TypeOfTransaction.ERROR_IN_PERFORMING_THE_OPERATION);
            save.setAccountNumberReceiver(transactionInDto.getAccountNumberReceiver());
            save.setAccountNumberSender(transactionInDto.getAccountNumberSender());
            save.setAmount(transactionInDto.getAmount());
            save.setUtilDate(new Date());
            save.setUtilTime(new Date());
            save.setIsTrue(false);
            transactionDao.save(save);
            throw new ThisAccountNumberIsNotForCurrentAccountException("this account number is not for current account ");
        } catch (ThisAccountNumberIsNotForCurrentAccountException e) {
            e.printStackTrace();
        }

        return save;
    }


    @Transactional
    public Transaction findById(Long id) {
        return transactionDao.findById(id).orElseThrow(RuntimeException::new);

    }

    @Transactional
    public void delete(Long id) {
        transactionDao.delete(findById(id));
    }

    @Transactional
    public List<Transaction> findTheLastThenTransactions(String accountNumber) {
        return transactionDao.findTransactionThen(accountNumber).stream().sorted((o1, o2) -> o2.getId().compareTo(o1.getId())).limit(10).toList();

    }


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
