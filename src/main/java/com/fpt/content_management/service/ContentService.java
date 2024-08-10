package com.fpt.content_management.service;

import com.fpt.content_management.dto.filter.ContentFilter;
import com.fpt.content_management.dto.request.ContentCreateDto;
import com.fpt.content_management.dto.response.ContentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentService {
    ContentResponseDto addContent (ContentCreateDto contentCreateDto);

    ContentResponseDto updateContent (Long id, ContentCreateDto contentCreateDto);

    void deleteContent (Long id);

    List<ContentResponseDto> getAllContent();

    ContentResponseDto getContent (Long id);

    Page<ContentResponseDto> getContents (ContentFilter contentFilter);
}
