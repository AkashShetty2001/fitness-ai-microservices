package com.fitness.activityService.dtos;

import java.time.LocalDateTime;
import java.util.Map;


import com.fitness.activityService.schema.ActivityType;

import lombok.Data;

@Data 
public class TrackActivityRequestDto {
    
    
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;

   

}
