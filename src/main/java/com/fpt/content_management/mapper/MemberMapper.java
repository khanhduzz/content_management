package com.fpt.content_management.mapper;

import com.fpt.content_management.dto.request.MemberUpdateRequestDto;
import com.fpt.content_management.dto.response.MemberResponseDto;
import com.fpt.content_management.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(target = "contents", ignore = true)
    Member toMember (MemberUpdateRequestDto memberUpdateRequestDto);

    MemberResponseDto toMemberResponseDto (Member member);

    @Mapping(target = "contents", ignore = true)
    Member updateMember (@MappingTarget Member member, MemberUpdateRequestDto memberUpdateRequestDto);
}
