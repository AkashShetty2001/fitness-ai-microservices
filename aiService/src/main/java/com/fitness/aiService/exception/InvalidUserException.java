package com.fitness.aiService.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(Long userId) {
        super("User with ID " + userId + " does not exist");
    }
}
