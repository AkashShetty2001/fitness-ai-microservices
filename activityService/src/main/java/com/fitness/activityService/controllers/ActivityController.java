package com.fitness.activityService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.activityService.dtos.TrackActivityRequestDto;
import com.fitness.activityService.dtos.TrackActivityResponseDto;
import com.fitness.activityService.services.ActivityService;
import com.fitness.activityService.utils.TrackActivityApiResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor 
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping 
    public ResponseEntity<TrackActivityApiResponse<TrackActivityResponseDto>> trackActivity(@RequestBody TrackActivityRequestDto requestDto){
        TrackActivityResponseDto  responseDto =activityService.trackActivity(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TrackActivityApiResponse.success("Activity tracked successfully", responseDto));

    }
    
}
