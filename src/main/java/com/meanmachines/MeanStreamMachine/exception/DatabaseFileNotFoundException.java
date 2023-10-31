package com.meanmachines.MeanStreamMachine.exception;

public class DatabaseFileNotFoundException extends RuntimeException{
    public DatabaseFileNotFoundException(String message) {
        super(message);
    }

    public DatabaseFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
