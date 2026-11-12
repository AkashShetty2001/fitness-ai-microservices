package com.fitness.activityService.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackActivityApiResponse<T> {

    private boolean success;
    private String error;
    private String message;
    private T data;

    public static <T> TrackActivityApiResponse<T> success(String message, T data) {
        return TrackActivityApiResponse.<T>builder()
                .success(true)
                .error(null)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> TrackActivityApiResponse<T> error(String message) {
        return TrackActivityApiResponse.<T>builder()
                .success(false)
                .error(message)
                .message(null)
                .data(null)
                .build();
    }
}
