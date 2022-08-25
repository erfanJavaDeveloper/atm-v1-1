package com.payeshgaran.service;

import com.payeshgaran.dto.account.AccountInDto;
import com.payeshgaran.entity.Account;
import com.payeshgaran.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
//    private final PasswordEncoder passwordEncoder;
//private static final DateFormat FORMAT = new SimpleDateFormat("####-####-####-####");

    @Transactional
    public void save(AccountInDto accountInDto) {
        Account account = accountInDto.converterDtoToEntity(accountInDto);
//        account.setPin(passwordEncoder.encode(account.getPin()));
        accountRepository.save(account);
    }

    @Transactional
    public void saveWithOutDto(Account account){
//        account.setPin(passwordEncoder.encode(account.getPin()));
        accountRepository.save(account);
    }
    @Transactional
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    @Transactional
    public void delete(Long id) {
        accountRepository.delete(findById(id));
    }

    //todo update account service @Erfan adine (1)
    @Transactional
    public void update_AccountNumber(String accountNumber , Long id ) {
        accountRepository.update_AccountNumber(accountNumber, id);
    }

    public void update_Account_Pin(String pin , Long id) {
        accountRepository.update_Account_Pin(pin, id);
    }
    public void update_Account_Balance(BigDecimal balance , Long id) {
        accountRepository.update_Account_Balance(balance, id);
    }

    public void update_Account_Type(Boolean type , Long id ) {
        accountRepository.update_Account_Type(type, id);
    }

    public void update_Account_Locked(Boolean locked , Long id ) {
        accountRepository.update_Account_Locked(locked, id);
    }

    public void update_AccountIncorrect_Attempts(Integer incorrectAttempts , Long id) {
        accountRepository.update_AccountIncorrect_Attempts(incorrectAttempts, id);
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public BigInteger getBalanceOfAccount(Long id){
        return accountRepository.findById(id).get().getBalance();

    }


}
