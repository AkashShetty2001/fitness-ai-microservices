package com.fitness.aiService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.aiService.dto.ActivityRecommendationRequestDto;
import com.fitness.aiService.dto.RecommendationResponseDto;
import com.fitness.aiService.dto.UserRecommendationsRequestDto;
import com.fitness.aiService.service.RecommendationService;
import com.fitness.aiService.util.RecommendationApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // Path variables are bound onto the request DTO by name, then validated
    @GetMapping("/user/{userId}")
    public ResponseEntity<RecommendationApiResponse<List<RecommendationResponseDto>>> getUserRecommendations(
            @Valid UserRecommendationsRequestDto requestDto) {
        List<RecommendationResponseDto> responseDtos = recommendationService.getUserRecommendations(requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RecommendationApiResponse.success("User recommendations retrieved successfully", responseDtos));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<RecommendationApiResponse<RecommendationResponseDto>> getActivityRecommendation(
            @Valid ActivityRecommendationRequestDto requestDto) {
        RecommendationResponseDto responseDto = recommendationService.getActivityRecommendation(requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RecommendationApiResponse.success("Activity recommendation retrieved successfully", responseDto));
    }
}
