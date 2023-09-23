package com.meanmachines.MeanStreamMachine.exception;

import java.net.MalformedURLException;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}