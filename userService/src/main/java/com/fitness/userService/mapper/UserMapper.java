package com.fitness.userService.mapper;

import com.fitness.userService.dto.RegisterUserRequestDto;
import com.fitness.userService.dto.RegisterUserResponseDto;
import com.fitness.userService.dto.UserProfileResponseDto;
import com.fitness.userService.schema.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserRequestDto requestDto);

    RegisterUserResponseDto toResponseDto(User user);

    UserProfileResponseDto toUserProfileResponseDto(User user);
}
