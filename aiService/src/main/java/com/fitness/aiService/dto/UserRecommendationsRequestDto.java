package com.fitness.aiService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserRecommendationsRequestDto {

    @NotNull(message = "userId is required")
    @Positive(message = "userId must be a positive number")
    private Long userId;
}
