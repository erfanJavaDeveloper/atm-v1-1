package com.payeshgaran.dao;

import com.payeshgaran.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

public interface AccountDao extends JpaRepository<Account, Long> {

    @Modifying
    @Query(nativeQuery = true)
    void update_AccountNumber(String accountNumber, Long id);


    @Modifying
    @Query(nativeQuery = true)
    void update_Account_Balance(BigDecimal balance, Long id);

    @Modifying
    @Query(nativeQuery = true)
    void update_AccountIncorrect_Attempts(Integer incorrectAttempts, Long id);


    @Modifying
    @Query(nativeQuery = true)
    void update_Account_Locked(Boolean locked, Long id);

    @Modifying
    @Query(nativeQuery = true)
    void update_Account_Pin(String pin, Long id);

    @Modifying
    @Query(nativeQuery = true)
    void update_Account_Type(Boolean type, Long id);

    @Query(nativeQuery = true)
    Account findByAccountNumber(@Param("accountNumber") String accountNumber);

}
