package com.fitness.userService.exceptions;

import com.fitness.userService.utils.UserApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserApiResponse.error(errors));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserApiResponse<Void>> handleUserExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(UserApiResponse.error(ex.getMessage()));
    }

    // Two concurrent registrations can both pass the existsBy check; the unique constraint is the final guard
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<UserApiResponse<Void>> handleIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(UserApiResponse.error("User with this email already exists"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserApiResponse<Void>> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(UserApiResponse.error(ex.getMessage()));
    }
}
