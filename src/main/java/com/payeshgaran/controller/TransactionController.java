package com.payeshgaran.controller;

import com.payeshgaran.model.transaction.TransactionInDto;
import com.payeshgaran.model.transaction.TransactionOutDto;
import com.payeshgaran.entity.Transaction;
import com.payeshgaran.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<String> save(@RequestBody TransactionInDto transactionInDto) {

        Transaction transaction = transactionService.save(transactionInDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(" don your transaction with Issue Tracking : " + transaction.getId());

    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(id + " deleted");
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TransactionOutDto> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        TransactionOutDto transactionOutDto = TransactionOutDto.convertEntityToOutDto(transaction);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(transactionOutDto);
    }

    @GetMapping("/findTheLastThenTransactions/{accountNumber}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Transaction>> findTheLastThenTransactions(@PathVariable String accountNumber) {
        List<Transaction> theLastThenTransactions = transactionService.findTheLastThenTransactions(accountNumber);
//        List<Transaction> theLastThenTransactions = transactionService.findTheLastThenTransactions(transactionFindInDto.getAccountNumber());
//        List<TransactionOutDto> transactionOutDtoList = TransactionOutDto.convertEntityListToOutDtoList(theLastThenTransactions);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(theLastThenTransactions);
    }

}
