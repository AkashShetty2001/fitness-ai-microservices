package com.fitness.userService.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String emailId) {
        super("User with email " + emailId + " already exists");
    }
}
