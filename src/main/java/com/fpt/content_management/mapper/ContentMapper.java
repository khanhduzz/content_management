package com.fpt.content_management.mapper;

import com.fpt.content_management.dto.request.ContentCreateDto;
import com.fpt.content_management.dto.response.ContentResponseDto;
import com.fpt.content_management.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = MemberMapper.class)
public interface ContentMapper {
    Content toContent (ContentCreateDto contentCreateDto);

    ContentResponseDto toContentResponseDto (Content content);

    @Mapping(target = "member", ignore = true)
    Content updateContent (@MappingTarget Content content, ContentCreateDto contentCreateDto);
}
