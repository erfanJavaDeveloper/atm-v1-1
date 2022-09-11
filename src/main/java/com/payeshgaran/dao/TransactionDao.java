package com.payeshgaran.dao;


import com.payeshgaran.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransactionDao extends JpaRepository<Transaction, Long> {



    @Query(nativeQuery = true)
    List<Transaction> findTransactionThen(@Param("accountNumber") String accountNumber);

}
