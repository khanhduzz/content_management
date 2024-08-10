package com.fpt.content_management.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentResponseDto {

    private Long id;
    private String title;
    private String brief;
    private String fullContent;
    private LocalDateTime dateCreated;
    private MemberResponseDto member;

}
