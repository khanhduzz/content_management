package com.fpt.content_management.service.impl;

import com.fpt.content_management.dto.filter.ContentFilter;
import com.fpt.content_management.dto.request.ContentCreateDto;
import com.fpt.content_management.dto.response.ContentResponseDto;
import com.fpt.content_management.entity.Content;
import com.fpt.content_management.entity.Member;
import com.fpt.content_management.exception.ContentAlreadyExistException;
import com.fpt.content_management.exception.ContentNotExistException;
import com.fpt.content_management.mapper.ContentMapper;
import com.fpt.content_management.repository.ContentRepository;
import com.fpt.content_management.repository.MemberRepository;
import com.fpt.content_management.service.ContentService;
import com.fpt.content_management.util.AuthUtil;
import com.fpt.content_management.util.PageSortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;
    private final ContentMapper contentMapper;
    private final AuthUtil authUtil;

    @Override
    @Transactional
    public ContentResponseDto addContent (ContentCreateDto contentCreateDto) {

        Content content = contentRepository.findContentByTitle(contentCreateDto.getTitle());
        if (content != null) {
            System.out.println(content.toString());
            throw new ContentAlreadyExistException();
        }

        Member member = authUtil.getMember();

        content = contentMapper.toContent(contentCreateDto);
        content.setMember(member);

        member.addContent(content);
        memberRepository.save(member);

        return contentMapper.toContentResponseDto(content);
    }

    @Override
    @Transactional
    public ContentResponseDto updateContent(Long id, ContentCreateDto contentCreateDto) {

        Content content = contentRepository.findById(id)
                .map(c -> {
                    var contentUpdate = contentMapper.updateContent(c, contentCreateDto);
                    contentRepository.save(contentUpdate);
                    return contentUpdate;
                })
                .orElseThrow(ContentNotExistException::new);

        return contentMapper.toContentResponseDto(content);
    }

    @Override
    @Transactional
    public void deleteContent(Long id) {
        contentRepository.findById(id)
                .map(c -> {
                    var member = authUtil.getMember();
                    member.removeContent(c);
                    memberRepository.save(member);
                    contentRepository.delete(c);
                    return c;
                }).orElseThrow(ContentNotExistException::new);
    }

    @Override
    public List<ContentResponseDto> getAllContent() {
        return contentRepository.findAll()
                .stream().map(contentMapper::toContentResponseDto)
                .toList();
    }

    @Override
    public ContentResponseDto getContent(Long id) {
        Content content = contentRepository.findContentById(id);
        if (content == null) {
            throw new ContentNotExistException();
        }
        return contentMapper.toContentResponseDto(content);
    }

    @Override
    public Page<ContentResponseDto> getContents(ContentFilter contentFilter) {

        Sort sort = Sort.by(PageSortUtil.parseSortDirection(contentFilter.getSortDir()), contentFilter.getOrderBy());

        Pageable pageable = PageSortUtil.createPageRequest(contentFilter.getPageNumber()
                , contentFilter.getPageSize(), sort);

        return contentRepository.findAll(
                Specification.where(ContentSpecification.hasTitle(contentFilter.getSearchString()))
                        .or(ContentSpecification.hasBrief(contentFilter.getSearchString()))
                        .or(ContentSpecification.hasAuthor(contentFilter.getSearchString())),
                pageable)
                .map(contentMapper::toContentResponseDto);
    }
}
