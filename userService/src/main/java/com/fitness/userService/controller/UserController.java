package com.fitness.userService.controller;

import com.fitness.userService.dto.RegisterUserRequestDto;
import com.fitness.userService.dto.RegisterUserResponseDto;
import com.fitness.userService.dto.UserProfileResponseDto;
import com.fitness.userService.service.UserService;
import com.fitness.userService.util.UserApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserApiResponse<UserProfileResponseDto>> getUserProfile(@PathVariable Long userId){
        UserProfileResponseDto userProfileResponseDto = userService.getUserProfile(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(UserApiResponse.success("User profile retrieved successfully", userProfileResponseDto));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.existsById(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserApiResponse<RegisterUserResponseDto>> registerUser(
            @Valid @RequestBody RegisterUserRequestDto requestDto) {
        RegisterUserResponseDto responseDto = userService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserApiResponse.success("User registered successfully", responseDto));
    }
}
