package com.fitness.aiService.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fitness.aiService.util.RecommendationApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RecommendationApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RecommendationApiResponse.error(errors));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<RecommendationApiResponse<Void>> handleInvalidUser(InvalidUserException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RecommendationApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(RecommendationNotFoundException.class)
    public ResponseEntity<RecommendationApiResponse<Void>> handleRecommendationNotFound(
            RecommendationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RecommendationApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(UserServiceUnavailableException.class)
    public ResponseEntity<RecommendationApiResponse<Void>> handleUserServiceUnavailable(
            UserServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(RecommendationApiResponse.error(ex.getMessage()));
    }
}
