package com.fitness.aiService.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationResponseDto {

    private String id;
    private String activityId;
    private Long userId;
    private List<String> improvements;
    private List<String> safetyMeasures;
    private List<String> suggestions;
    private LocalDateTime createdAt;
}
