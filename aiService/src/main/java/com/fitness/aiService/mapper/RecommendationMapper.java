package com.fitness.aiService.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.fitness.aiService.dto.RecommendationResponseDto;
import com.fitness.aiService.schema.Recommendation;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    RecommendationResponseDto toResponseDto(Recommendation recommendation);

    List<RecommendationResponseDto> toResponseDtos(List<Recommendation> recommendations);
}
