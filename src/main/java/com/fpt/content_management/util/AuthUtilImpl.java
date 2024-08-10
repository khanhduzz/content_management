package com.fpt.content_management.util;

import com.fpt.content_management.entity.Member;
import com.fpt.content_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUtilImpl implements AuthUtil {

    private final MemberRepository memberRepository;

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Member getMember() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByUsernameIgnoreCase(name);
    }
}
