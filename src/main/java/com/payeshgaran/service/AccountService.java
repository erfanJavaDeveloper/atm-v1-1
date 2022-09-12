package com.payeshgaran.service;

import com.payeshgaran.dao.AccountDao;
import com.payeshgaran.entity.Account;
import com.payeshgaran.entity.permission.Role;
import com.payeshgaran.model.account.AccountInDto;
import com.payeshgaran.validation.AccountValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    Random rnd = new Random();


    @Transactional
    public Account save(AccountInDto accountInDto) {
        Account account = accountInDto.converterDtoToEntity(accountInDto);
        account.setAccountNumber("6037" + rnd.nextInt(999999));
        account.setPin(passwordEncoder.encode(account.getPin()));
        account.setRoles(Set.of(Role.ADMIN));
        account.setIsAccountNonExpired(true);
        account.setIsCredentialsNonExpired(true);
        account.setIsAccountNonLocked(true);
        account.setIsEnable(true);
        return accountDao.save(account);
    }

    @Transactional
    public void saveWithOutDto(Account account) {
        account.setPin(passwordEncoder.encode(account.getPin()));
        account.setRoles(Set.of(Role.ADMIN));
        account.setIsAccountNonExpired(true);
        account.setIsCredentialsNonExpired(true);
        account.setIsAccountNonLocked(true);
        account.setIsEnable(true);
        accountDao.save(account);
    }

    @Transactional
    public Account findById(Long id) {
        return accountDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void delete(Long id) {
        accountDao.delete(findById(id));
    }

    //todo update account service @Erfan adine (1)
    @Transactional
    public void update_AccountNumber(String accountNumber, Long id) {
        accountDao.update_AccountNumber(accountNumber, id);
    }

    public void update_Account_Pin(String pin, Long id) {
        accountDao.update_Account_Pin(pin, id);
    }

    public void update_Account_Balance(BigDecimal balance, Long id) {
        accountDao.update_Account_Balance(balance, id);
    }

    public void update_Account_Type(Boolean type, Long id) {
        accountDao.update_Account_Type(type, id);
    }

    public void update_Account_Locked(Boolean locked, Long id) {
        accountDao.update_Account_Locked(locked, id);
    }

    public void update_AccountIncorrect_Attempts(Integer incorrectAttempts, Long id) {
        accountDao.update_AccountIncorrect_Attempts(incorrectAttempts, id);
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber);
    }

    public BigInteger getBalanceOfAccount(Long id) {
        if (accountDao.findById(id).isPresent()) {
            return accountDao.findById(id).get().getBalance();
        }
        return null;
    }

}
