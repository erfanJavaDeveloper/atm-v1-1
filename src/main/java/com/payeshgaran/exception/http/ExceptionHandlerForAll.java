package com.payeshgaran.exception.http;

import com.payeshgaran.exception.exNew.AccountNullPointException;
import com.payeshgaran.exception.exNew.ThisAccountNumberIsNotForCurrentAccountException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

public class ExceptionHandlerForAll {

//    @ExceptionHandler(value = AccountNullPointException.class)
//    public ResponseEntity handleBlogAlreadyExistsException(AccountNullPointException accountNullPointException) {
//        return new ResponseEntity("Blog already exists" + accountNullPointException , HttpStatus.BAD_REQUEST);
//
//    }
//
//    @ExceptionHandler(value = AccountNullPointException.class)
//    public ResponseEntity handleBlogAlreadyExistsException(ThisAccountNumberIsNotForCurrentAccountException thisAccountNumberIsNotForCurrentAccountException) {
//        return new ResponseEntity("Blog already exists" + thisAccountNumberIsNotForCurrentAccountException , HttpStatus.BAD_REQUEST);
//
//    }

    @ExceptionHandler(ThisAccountNumberIsNotForCurrentAccountException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ThisAccountNumberIsNotForCurrentAccountException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(" This AccountNumber Is Not For Current Account Exception ");
        return message;
    }

    @ExceptionHandler(AccountNullPointException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(AccountNullPointException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(" Account is null");
        return message;
    }

//    @ExceptionHandler(value = AccountNullPointException.class)
//    public ResponseEntity handleBlogAlreadyExistsException(AccountNullPointException accountNullPointException) {
//        return new ResponseEntity("Blog already exists" + accountNullPointException , HttpStatus.BAD_REQUEST);
//
//    }
}



