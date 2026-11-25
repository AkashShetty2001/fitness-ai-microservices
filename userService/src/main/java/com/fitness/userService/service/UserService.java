package com.fitness.userService.service;

import com.fitness.userService.dto.RegisterUserRequestDto;
import com.fitness.userService.dto.RegisterUserResponseDto;
import com.fitness.userService.dto.UserProfileResponseDto;
import com.fitness.userService.exception.UserAlreadyExistsException;
import com.fitness.userService.exception.UserNotFoundException;
import com.fitness.userService.mapper.UserMapper;
import com.fitness.userService.repository.UserRepository;
import com.fitness.userService.schema.User;
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

    public UserProfileResponseDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        return userMapper.toUserProfileResponseDto(user);
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
}
