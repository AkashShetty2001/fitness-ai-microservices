package com.fitness.activityService.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fitness.activityService.util.TrackActivityApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TrackActivityApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TrackActivityApiResponse.error(errors));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<TrackActivityApiResponse<Void>> handleInvalidUser(InvalidUserException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TrackActivityApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(UserServiceUnavailableException.class)
    public ResponseEntity<TrackActivityApiResponse<Void>> handleUserServiceUnavailable(
            UserServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(TrackActivityApiResponse.error(ex.getMessage()));
    }
}
