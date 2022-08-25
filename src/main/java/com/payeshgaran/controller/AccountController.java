package com.payeshgaran.controller;

import com.payeshgaran.dto.account.AccountInDto;
import com.payeshgaran.dto.account.AccountOutDto;
import com.payeshgaran.dto.account.update.*;
import com.payeshgaran.entity.Account;
import com.payeshgaran.repository.AccountRepository;
import com.payeshgaran.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody AccountInDto accountInDto) {
        accountService.save(accountInDto);
        return ResponseEntity.status(HttpStatus.OK).body("account with  id: "+accountService.findByAccountNumber(accountInDto.getAccountNumber()).getId() +"  saved ");
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<AccountOutDto> findById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        AccountOutDto accountOutDto = AccountOutDto.convertEntityToOutDto(account);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountOutDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }

    //todo update account service @Erfan adine (1)

    @PutMapping("/update_AccountNumber/{id}")
    public void update_AccountNumber(@RequestBody Update_AccountNumber_DTO accountNumber, @PathVariable Long id) {
        accountService.update_AccountNumber(accountNumber.getAccountNumber(), id);
    }
    @PutMapping("/update_Account_Pin/{id}")
    public void update_Account_Pin(@RequestBody Update_Account_Pin_DTO pin, @PathVariable Long id) {
        accountService.update_Account_Pin(pin.getPin(), id);
    }
    @PutMapping("/update_Account_Balance/{id}")
    public void update_Account_Balance(@RequestBody Update_Account_Balance_DTO balance, @PathVariable Long id) {
        accountService.update_Account_Balance(balance.getBalance(), id);
    }
    @PutMapping("/update_Account_Type/{id}")
    public void update_Account_Type(@RequestBody Update_Account_Type_DTO type, @PathVariable Long id) {
        accountService.update_Account_Type(type.getType(), id);
    }
    @PutMapping("/update_Account_Locked/{id}")
    public void update_Account_Locked(@RequestBody Update_Account_Locked_DTO locked,@PathVariable Long id) {
        accountService.update_Account_Locked(locked.getLocked(), id);
    }
    @PutMapping("/update_AccountIncorrect_Attempts/{id}")
    public void update_AccountIncorrect_Attempts(@RequestBody Update_AccountIncorrect_Attempts_DTO incorrectAttempts,@PathVariable Long id) {
        accountService.update_AccountIncorrect_Attempts(incorrectAttempts.getIncorrectAttempts(), id);
    }
    @GetMapping("/findByAN/{accountNumber}")
    public ResponseEntity<String> findByAccountNumber( @PathVariable String accountNumber){
        accountRepository.findByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body("find testing :" + accountNumber);
    }

    @GetMapping("/findBalanceOfAccount/{id}")
    public ResponseEntity<String> getBalanceOfAccount(@PathVariable Long id){
        BigInteger balanceOfAccount = accountService.getBalanceOfAccount(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body("balance of this id :" + balanceOfAccount);
    }

}
