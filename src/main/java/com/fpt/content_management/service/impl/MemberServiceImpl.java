package com.fpt.content_management.service.impl;

import com.fpt.content_management.dto.request.MemberUpdateRequestDto;
import com.fpt.content_management.dto.response.MemberResponseDto;
import com.fpt.content_management.entity.Member;
import com.fpt.content_management.mapper.MemberMapper;
import com.fpt.content_management.repository.MemberRepository;
import com.fpt.content_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberResponseDto updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByUsernameIgnoreCase(name);
        member = memberMapper.updateMember(member, memberUpdateRequestDto);
        return memberMapper.toMemberResponseDto(memberRepository.save(member));
    }

    @Override
    public MemberResponseDto getMember() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberMapper.toMemberResponseDto(memberRepository.findByUsernameIgnoreCase(name));
    }
}
