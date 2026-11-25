package com.fitness.activityService.service;

import org.springframework.stereotype.Service;

import com.fitness.activityService.client.UserServiceClient;
import com.fitness.activityService.dto.TrackActivityRequestDto;
import com.fitness.activityService.exception.InvalidUserException;
import com.fitness.activityService.dto.TrackActivityResponseDto;
import com.fitness.activityService.mapper.ActivityMapper;
import com.fitness.activityService.repository.ActivityRepository;
import com.fitness.activityService.schema.Activity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final UserServiceClient userServiceClient;

    public TrackActivityResponseDto trackActivity(TrackActivityRequestDto requestDto){

        if (!userServiceClient.userExists(requestDto.getUserId())) {
            throw new InvalidUserException(requestDto.getUserId());
        }

        Activity activity = activityMapper.toActivity(requestDto);

        Activity savedActivity = activityRepository.save(activity);

        return activityMapper.toResponseDto(savedActivity);

    }

    
}
