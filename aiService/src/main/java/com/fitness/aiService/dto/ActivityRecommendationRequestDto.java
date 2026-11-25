package com.fitness.aiService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActivityRecommendationRequestDto {

    @NotBlank(message = "activityId must not be blank")
    private String activityId;
}
