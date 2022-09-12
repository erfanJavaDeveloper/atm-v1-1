package com.payeshgaran.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    private TypeOfTransaction type = TypeOfTransaction.IN_PROGRESS;

    private String accountNumberSender;
    private String accountNumberReceiver;
    @Min(value = 500)
    private BigInteger amount;

    private Boolean isTrue;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate = new Date();

    @Basic
    @Temporal(TemporalType.TIME)
    private java.util.Date utilTime = new Date();

}
