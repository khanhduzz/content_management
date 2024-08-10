package com.fpt.content_management.security;

import com.fpt.content_management.entity.Member;
import com.fpt.content_management.exception.UserNotFoundException;
import com.fpt.content_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.map(value -> User.withUsername(value.getUsername())
                .password(value.getPassword())
                .build()).orElseThrow(UserNotFoundException::new);
    }
}
