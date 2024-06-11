package com.backend.Employee_Management_Application.Service;


import com.backend.Employee_Management_Application.DTO.JwtAuthResponse;
import com.backend.Employee_Management_Application.DTO.LoginRequestDto;
import com.backend.Employee_Management_Application.DTO.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginRequestDto loginRequestDto);
}

