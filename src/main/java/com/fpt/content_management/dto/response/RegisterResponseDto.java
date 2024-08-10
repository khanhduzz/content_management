package com.fpt.content_management.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class RegisterResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String username;
}
