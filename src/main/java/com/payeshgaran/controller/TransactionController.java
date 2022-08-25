package com.payeshgaran.controller;


import com.payeshgaran.dto.transaction.TransactionFindInDto;
import com.payeshgaran.dto.transaction.TransactionInDto;
import com.payeshgaran.dto.transaction.TransactionOutDto;
import com.payeshgaran.entity.Transaction;
import com.payeshgaran.service.AccountService;
import com.payeshgaran.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("/{accountId}")
    public ResponseEntity<String> save( @RequestBody TransactionInDto transactionInDto, @PathVariable Long accountId) {

        Transaction transaction = transactionService.save(transactionInDto, accountId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(" don your transaction with Issue Tracking : " + transaction.getId());

    }

    //    public ResponseEntity<String> delete(@Valid @RequestParam Long id, @PathVariable String id) {
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(id + " deleted");
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TransactionOutDto> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        TransactionOutDto transactionOutDto = TransactionOutDto.convertEntityToOutDto(transaction);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(transactionOutDto);
    }

    @GetMapping("/findTheLastThenTransactions")
    public ResponseEntity<List<TransactionOutDto>> findTheLastThenTransactions(@RequestBody TransactionFindInDto transactionFindInDto){
        List<Transaction> theLastThenTransactions = transactionService.findTheLastThenTransactions(transactionFindInDto.getAccountNumber());
        List<TransactionOutDto> transactionOutDtoList = TransactionOutDto.convertEntityListToOutDtoList(theLastThenTransactions);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(transactionOutDtoList);
    }




}
