package com.fitness.activityService.exceptions;

public class UserServiceUnavailableException extends RuntimeException {

    public UserServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceUnavailableException(String message) {
        super(message);
    }
}
