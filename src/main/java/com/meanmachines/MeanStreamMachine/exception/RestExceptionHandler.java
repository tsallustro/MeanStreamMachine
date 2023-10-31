package com.meanmachines.MeanStreamMachine.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler{
    @Value("${exceptions.STORAGE_RESPONSE}")
    String STORAGE_RESPONSE;

    @Value("${exceptions.DBFAIL_RESPONSE}")
    String DBFAIL_RESPONSE;

    @Value("${exceptions.STACK_VERBOSE}")
    Boolean STACK_VERBOSE;

    @ExceptionHandler(DatabaseFileNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleDatabaseException(DatabaseFileNotFoundException ex){
        log.error("The server has encountered a database FNF exception with stack trace: ");
        if(STACK_VERBOSE) ex.printStackTrace();
        ExceptionResponse response = buildResponseEntity(DBFAIL_RESPONSE, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StorageException.class)
    protected ResponseEntity<ExceptionResponse> handleStorageException(StorageException ex){
        log.error("The server has encountered a storage exception with stack trace: ");
        if(STACK_VERBOSE) ex.printStackTrace();
        ExceptionResponse response = buildResponseEntity(STORAGE_RESPONSE, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionResponse buildResponseEntity(String message, Throwable cause, HttpStatus status){
        return new ExceptionResponse(message, cause.toString(), status);

    }

}
