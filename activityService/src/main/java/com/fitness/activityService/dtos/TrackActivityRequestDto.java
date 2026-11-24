package com.fitness.activityService.dtos;

import java.time.LocalDateTime;
import java.util.Map;


import com.fitness.activityService.schema.ActivityType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TrackActivityRequestDto {


    // @NotBlank only applies to CharSequence; a Long id needs @NotNull
    @NotNull
    private Long userId;
    @NotNull
    private ActivityType type;
    @PositiveOrZero
    private Integer duration;
    @PositiveOrZero
    private Integer caloriesBurned;
    @NotNull
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;

   

}
