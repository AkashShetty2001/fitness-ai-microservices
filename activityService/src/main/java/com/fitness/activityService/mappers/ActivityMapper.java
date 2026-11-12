package com.fitness.activityService.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fitness.activityService.dtos.TrackActivityRequestDto;
import com.fitness.activityService.dtos.TrackActivityResponseDto;
import com.fitness.activityService.schema.Activity;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Activity toActivity(TrackActivityRequestDto requestDto);

    TrackActivityResponseDto toResponseDto(Activity activity);

}
