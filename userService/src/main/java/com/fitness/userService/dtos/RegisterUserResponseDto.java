package com.fitness.userService.dtos;

import com.fitness.userService.schemas.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private UserRole role;
    private LocalDateTime createdAt;
}
