package com.fitness.userService.controllers;

import com.fitness.userService.dtos.RegisterUserRequestDto;
import com.fitness.userService.dtos.RegisterUserResponseDto;
import com.fitness.userService.services.UserService;
import com.fitness.userService.utils.UserApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserApiResponse<RegisterUserResponseDto>> registerUser(
            @Valid @RequestBody RegisterUserRequestDto requestDto) {
        RegisterUserResponseDto responseDto = userService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserApiResponse.success("User registered successfully", responseDto));
    }
}
