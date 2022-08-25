package com.payeshgaran.repository;

import com.payeshgaran.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Modifying
    @Query("update Account a set a.accountNumber=:accountNumber where a.id=:id ")
    void update_AccountNumber(String accountNumber , Long id);


    @Modifying
    @Query("update Account a set a.balance=:balance where a.id=:id ")
    void update_Account_Balance(BigDecimal balance , Long id);

    @Modifying
    @Query("update Account a set a.incorrectAttempts=:incorrectAttempts where a.id=:id ")
    void update_AccountIncorrect_Attempts(Integer incorrectAttempts , Long id);


    @Modifying
    @Query("update Account a set a.locked=:locked  where a.id=:id ")
    void update_Account_Locked(Boolean locked , Long id);

    @Modifying
    @Query("update Account a set a.pin=:pin where a.id=:id ")
    void update_Account_Pin(String pin , Long id);

    @Modifying
    @Query("update Account a set a.type=:type where a.id=:id ")
    void update_Account_Type(Boolean type , Long id);


    Account findByAccountNumber(String accountNumber);





}
