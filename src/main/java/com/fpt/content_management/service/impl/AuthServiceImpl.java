package com.fpt.content_management.service.impl;

import com.fpt.content_management.dto.request.RegisterRequestDto;
import com.fpt.content_management.dto.response.RegisterResponseDto;
import com.fpt.content_management.entity.Member;
import com.fpt.content_management.exception.MemberAlreadyExistException;
import com.fpt.content_management.repository.MemberRepository;
import com.fpt.content_management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponseDto register (RegisterRequestDto registerRequestDto) {
        Member member = memberRepository.findByEmailIgnoreCase(registerRequestDto.getEmail());
        if (member != null) {
            throw new MemberAlreadyExistException("Email already exist");
        }
        member = memberRepository.findByUsernameIgnoreCase(registerRequestDto.getUsername());
        if (member != null) {
            throw new MemberAlreadyExistException("Username already exist");
        }
        member = new Member();
        member.setEmail(registerRequestDto.getEmail());
        member.setUsername(registerRequestDto.getUsername());
        member.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        memberRepository.save(member);
        return RegisterResponseDto.builder()
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .build();
    }
}
