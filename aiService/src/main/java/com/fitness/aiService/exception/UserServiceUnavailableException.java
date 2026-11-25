package com.fitness.aiService.exception;

public class UserServiceUnavailableException extends RuntimeException {

    public UserServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceUnavailableException(String message) {
        super(message);
    }
}
