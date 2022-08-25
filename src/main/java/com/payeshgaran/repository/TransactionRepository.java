package com.payeshgaran.repository;


import com.payeshgaran.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Modifying
//    @Query("update Transaction t set t.type=:type where t.id=:id ")
//    void update_transaction_type(Boolean type , Long id);
//
//    @Modifying
//    @Query("update Transaction t set t.accountNumber_Receiver=:accountNoSender where t.id=:id ")
//    void accountNumber_Receiver(Long accountNoSender , Long id);
//
//    @Modifying
//    @Query("update Transaction t set t.amount=:amount where t.id=:id ")
//    void update_Account_amount(BigDecimal amount , Long id);

    //    @Modifying
//    @Query("update Transaction t set t.accountNumber_Receiver=:accountNumber_Receiver , t.accountNumber_Sender=:accountNumber_Sender ,t.account=:account ,t.amount=:amount ")
//    void update(String  accountNumber_Receiver , String accountNumber_Sender , Account account, BigDecimal amount);
//    @Query(nativeQuery = true , value = "select  * from transaction as t where  t. ")
    @Query(nativeQuery = true , value = "select * from Transaction t where t.account_number_receiver  =:accountNumber  or t.account_number_sender =:accountNumber ORDER BY  t.id  desc LIMIT 10")
    List<Transaction> findTransactionThen(@Param("accountNumber") String accountNumber);

}
