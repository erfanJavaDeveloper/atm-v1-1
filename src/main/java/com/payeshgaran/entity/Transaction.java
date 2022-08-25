package com.payeshgaran.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeOfTransaction type =TypeOfTransaction.IN_PROGRESS;

    //    @Column(name = "accountNumber_Sender")
    private String accountNumberSender;

    //    @Column(name = "accountNumber_Receiver")
    private String accountNumberReceiver;

    private BigInteger amount;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate = new Date();

    @Basic
    @Temporal(TemporalType.TIME)
    private java.util.Date utilTime = new Date();

}
