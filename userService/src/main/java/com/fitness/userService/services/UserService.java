package com.fitness.userService.services;

import com.fitness.userService.dtos.RegisterUserRequestDto;
import com.fitness.userService.dtos.RegisterUserResponseDto;
import com.fitness.userService.exceptions.UserAlreadyExistsException;
import com.fitness.userService.mappers.UserMapper;
import com.fitness.userService.repositories.UserRepository;
import com.fitness.userService.schemas.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto) {
        String normalizedEmail = requestDto.getEmailId().trim().toLowerCase();

        if (userRepository.existsByEmailId(normalizedEmail)) {
            throw new UserAlreadyExistsException(normalizedEmail);
        }

        User user = userMapper.toEntity(requestDto);
        user.setEmailId(normalizedEmail);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        return userMapper.toResponseDto(userRepository.save(user));
    }
}
