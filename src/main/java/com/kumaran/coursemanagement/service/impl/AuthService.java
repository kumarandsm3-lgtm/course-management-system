package com.kumaran.coursemanagement.service;

import com.kumaran.coursemanagement.dto.AuthResponseDto;
import com.kumaran.coursemanagement.dto.LoginRequestDto;
import com.kumaran.coursemanagement.dto.RegisterRequestDto;

public interface AuthService {

    String register(RegisterRequestDto requestDto);

    AuthResponseDto login(LoginRequestDto requestDto);
}