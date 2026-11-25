package com.fitness.aiService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fitness.aiService.client.UserServiceClient;
import com.fitness.aiService.dto.ActivityRecommendationRequestDto;
import com.fitness.aiService.dto.RecommendationResponseDto;
import com.fitness.aiService.dto.UserRecommendationsRequestDto;
import com.fitness.aiService.exception.InvalidUserException;
import com.fitness.aiService.exception.RecommendationNotFoundException;
import com.fitness.aiService.mapper.RecommendationMapper;
import com.fitness.aiService.repository.RecommendationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;
    private final UserServiceClient userServiceClient;

    public List<RecommendationResponseDto> getUserRecommendations(UserRecommendationsRequestDto requestDto) {
        Long userId = requestDto.getUserId();

        if (!userServiceClient.userExists(userId)) {
            throw new InvalidUserException(userId);
        }

        return recommendationMapper.toResponseDtos(
                recommendationRepository.findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(userId));
    }

    public RecommendationResponseDto getActivityRecommendation(ActivityRecommendationRequestDto requestDto) {
        String activityId = requestDto.getActivityId();

        return recommendationRepository
                .findFirstByActivityIdAndDeletedAtIsNullOrderByCreatedAtDesc(activityId)
                .map(recommendationMapper::toResponseDto)
                .orElseThrow(() -> new RecommendationNotFoundException(activityId));
    }
}
