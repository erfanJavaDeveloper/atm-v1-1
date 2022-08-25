package com.payeshgaran.dto.transaction;

import com.payeshgaran.entity.Transaction;
import com.payeshgaran.entity.TypeOfTransaction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class TransactionInDto {

    @ApiModelProperty(required = true)
    private TypeOfTransaction type = TypeOfTransaction.IN_PROGRESS;

    @ApiModelProperty(required = true)
    private String accountNumberSender;

    @ApiModelProperty(required = true)
    private String accountNumberReceiver;

    @ApiModelProperty(required = true)
    private BigInteger amount;

//    @ApiModelProperty(required = true)
//    private Long SenderId;
//
//    @ApiModelProperty(required = true)
//    private Long ReceiverId;

    public static Transaction convertDtoToEntity(TransactionInDto transactionInDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionInDto.getAmount());
        transaction.setAccountNumberSender(transactionInDto.getAccountNumberSender());
        transaction.setAccountNumberReceiver(transactionInDto.getAccountNumberReceiver());
        transaction.setType(transactionInDto.getType());
        return transaction;
    }


}
