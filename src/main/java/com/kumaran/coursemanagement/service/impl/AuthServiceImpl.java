package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.AuthResponseDto;
import com.kumaran.coursemanagement.dto.LoginRequestDto;
import com.kumaran.coursemanagement.dto.RegisterRequestDto;
import com.kumaran.coursemanagement.entity.AppUser;
import com.kumaran.coursemanagement.repository.AppUserRepository;
import com.kumaran.coursemanagement.security.JwtService;
import com.kumaran.coursemanagement.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository repository;
    private final JwtService jwtService;

    public AuthServiceImpl(
            AppUserRepository repository,
            JwtService jwtService) {

        this.repository = repository;
        this.jwtService = jwtService;
    }

    @Override
    public String register(RegisterRequestDto requestDto) {

        AppUser user = new AppUser();

        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setRole("USER");

        repository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponseDto login(LoginRequestDto requestDto) {

        AppUser user = repository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDto(token);
    }
}