package com.fpt.content_management.service;

import com.fpt.content_management.dto.request.MemberUpdateRequestDto;
import com.fpt.content_management.dto.response.MemberResponseDto;
import com.fpt.content_management.entity.Member;

public interface MemberService {
    MemberResponseDto updateMember (MemberUpdateRequestDto memberUpdateRequestDto);

    MemberResponseDto getMember ();
}
