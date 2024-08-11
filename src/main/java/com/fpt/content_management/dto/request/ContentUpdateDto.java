package com.fpt.content_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentUpdateDto {
    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 50, message = "Max length is 50")
    private String title;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Max length is 255")
    private String brief;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    private String fullContent;
}
