package com.fitness.userService.mappers;

import com.fitness.userService.dtos.RegisterUserRequestDto;
import com.fitness.userService.dtos.RegisterUserResponseDto;
import com.fitness.userService.schemas.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserRequestDto requestDto);

    RegisterUserResponseDto toResponseDto(User user);
}
