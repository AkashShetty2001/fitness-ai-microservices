package com.fitness.userService.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApiResponse<T>{

    private boolean success;
    private String error;
    private String message;
    private T data;

    public static<T>UserApiResponse<T> success(String message,T data){
        return UserApiResponse.<T>builder()
            .success(true)
                .error(null)
                .message(message)
                .data(data)
                .build();
    }

    public static<T>UserApiResponse<T> error(String message){
        return UserApiResponse.<T>builder()
                .success(false)
                .error(message)
                .message(null)
                .data(null)
                .build();
    }
}
