package com.fitness.userService.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String emailId) {
        super("User with email " + emailId + " already exists");
    }
}
