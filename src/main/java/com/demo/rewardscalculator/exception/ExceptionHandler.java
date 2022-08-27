package com.demo.rewardscalculator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.demo.rewardscalculator.dto.ErrorResponse;


@ControllerAdvice
public class ExceptionHandler {	

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
    	ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(404);
        errorResponse.setErrorMessage("CUSTOMER_NOT_FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = TransactionsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTransactionsNotFoundException(TransactionsNotFoundException transactionsNotFoundException) {
    	ErrorResponse errorResponse = new ErrorResponse();
    	errorResponse.setErrorCode(404);
    	errorResponse.setErrorMessage("TRANSACTIONS_NOT_FOUND_FOR_REQUESTED_CUSTOMER");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException) {
    	ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(500);
        errorResponse.setErrorMessage("INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
