package com.kumaran.coursemanagement.controller;

import com.kumaran.coursemanagement.dto.AuthResponseDto;
import com.kumaran.coursemanagement.dto.LoginRequestDto;
import com.kumaran.coursemanagement.dto.RegisterRequestDto;
import com.kumaran.coursemanagement.response.ApiResponse;
import com.kumaran.coursemanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequestDto requestDto) {

        String response = service.register(requestDto);

        ApiResponse<String> apiResponse =
                new ApiResponse<>(true, "User registered successfully", response);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(
            @Valid @RequestBody LoginRequestDto requestDto) {

        AuthResponseDto response = service.login(requestDto);

        ApiResponse<AuthResponseDto> apiResponse =
                new ApiResponse<>(true, "Login successful", response);

        return ResponseEntity.ok(apiResponse);
    }
}