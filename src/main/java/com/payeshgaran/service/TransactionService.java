package com.payeshgaran.service;

import com.payeshgaran.model.transaction.TransactionInDto;
import com.payeshgaran.entity.Account;
import com.payeshgaran.entity.Transaction;
import com.payeshgaran.exception.exNew.ThisAccountNumberIsNotForCurrentAccountException;
import com.payeshgaran.dao.TransactionDao;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payeshgaran.validation.TransactionValidation;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
public class TransactionService {


    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private AccountService accountService;


    private TransactionValidation transactionValidation = new TransactionValidation(this);

    public TransactionService(TransactionValidation transactionValidation) {
        this.transactionValidation = transactionValidation;
    }



    @Transactional
    public Transaction save(TransactionInDto transactionInDto) {
        if (transactionValidation.checkCurrentUser(transactionInDto)) {
            Transaction save = transactionDao.save(TransactionInDto.convertDtoToEntity(transactionInDto));

            Account sender = accountService.findByAccountNumber(save.getAccountNumberSender());
            sender.setTransaction(List.of(save));
            sender.setBalance(sender.getBalance().subtract(save.getAmount()));

            Account receiver = accountService.findByAccountNumber(save.getAccountNumberReceiver());
            receiver.setTransaction(List.of(save));
            receiver.setBalance(receiver.getBalance().add(save.getAmount()));


            return save;
        }
        throw new ThisAccountNumberIsNotForCurrentAccountException();
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


}
