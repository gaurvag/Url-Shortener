package com.project.urlshortener.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Url {
    @Id
    private Long urlId;
    private String originalUrl;
    private String shortKey;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private Long userId;
    private Long numberOfClicks;
}
