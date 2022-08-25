package com.payeshgaran.exception.http;

import com.payeshgaran.exception.exNew.AccountNullPointException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandlerForAll {

    @ExceptionHandler(value = AccountNullPointException.class)
    public ResponseEntity handleBlogAlreadyExistsException(AccountNullPointException accountNullPointException) {
        return new ResponseEntity("Blog already exists" + accountNullPointException , HttpStatus.BAD_REQUEST);

    }
}



