package com.project.urlshortener.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UrlResponseDto {
    private Long urlId;
    private String originalUrl;
    private String shortLink;
    private Long numberOfClicks;
    private LocalDateTime expirationDate;
}
