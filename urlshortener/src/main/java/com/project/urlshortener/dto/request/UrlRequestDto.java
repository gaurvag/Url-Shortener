package com.project.urlshortener.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequestDto {

    @NotBlank(message = "url can't be blank")
    private String url;
    private Long userId;
    private LocalDateTime expirationDate;
}
