package com.fitness.activityService.dtos;

import java.time.LocalDateTime;
import java.util.Map;


import com.fitness.activityService.schema.ActivityType;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class TrackActivityResponseDto {
    
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
