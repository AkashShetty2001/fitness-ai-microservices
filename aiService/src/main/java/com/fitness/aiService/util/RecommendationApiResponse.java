package com.fitness.aiService.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationApiResponse<T> {

    private boolean success;
    private String error;
    private String message;
    private T data;

    public static <T> RecommendationApiResponse<T> success(String message, T data) {
        return RecommendationApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> RecommendationApiResponse<T> error(String message) {
        return RecommendationApiResponse.<T>builder()
                .success(false)
                .error(message)
                .build();
    }
}
