package com.fpt.content_management.service;

import com.fpt.content_management.dto.request.RegisterRequestDto;
import com.fpt.content_management.dto.response.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register (RegisterRequestDto registerRequestDto);
}
