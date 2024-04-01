package com.project.urlshortener.service.impl;

import com.project.urlshortener.dto.request.UrlRequestDto;
import com.project.urlshortener.dto.response.UrlResponseDto;
import com.project.urlshortener.exception.UrlExpiredException;
import com.project.urlshortener.exception.UrlNotFoundException;
import com.project.urlshortener.model.Sequence;
import com.project.urlshortener.model.Url;
import com.project.urlshortener.repository.UrlRepository;
import com.project.urlshortener.service.EncodingService;
import com.project.urlshortener.service.SequenceService;
import com.project.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.project.urlshortener.util.Constant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {
    private final EncodingService encodingService;
    private final SequenceService sequenceService;
    private final UrlRepository urlRepository;
    private final ServerProperties serverProperties;
    @Override
    public UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto) {
        Long generateUrlId = generateUrlId();
        String shortKey = generateShortKey(generateUrlId);

        Url url = getUrl(urlRequestDto, generateUrlId, shortKey);
        urlRepository.save(url);

        return getUrlResponseDto(generateUrlId, shortKey, url);
    }

    @Override
    public void redirectToOriginalUrl(String shortKey, HttpServletResponse response) throws IOException {
        Url url = getUrlByShortKey(shortKey);
        url.setNumberOfClicks(url.getNumberOfClicks() + 1);
        urlRepository.save(url);
        response.sendRedirect(url.getOriginalUrl());
    }

    @Override
    public Long getNumberOfClicksByShortKey(String shortKey) {
        Url url = getUrlByShortKey(shortKey);
        return url.getNumberOfClicks();
    }

    private Url getUrlByShortKey(String shortKey) {
        Url url = urlRepository.findByShortKey(shortKey);
        if(Objects.isNull(url)){
            log.error("UrlServiceImpl:redirectToOriginalUrl Url not found");
            throw new UrlNotFoundException("Url not found");
        }
        if(url.getExpirationDate().isBefore(LocalDateTime.now())){
            log.info("UrlServiceImpl:redirectToOriginalUrl Url with id: {} is expired", url.getUrlId());
            throw new UrlExpiredException("Url is expired, create new short url");
        }
        return url;
    }

    private Url getUrl(UrlRequestDto urlRequestDto, Long generateUrlId, String shortKey) {
        return Url.builder()
                  .urlId(generateUrlId)
                  .originalUrl(urlRequestDto.getUrl())
                  .shortKey(shortKey)
                  .creationDate(LocalDateTime.now())
                  .expirationDate(Optional.ofNullable(urlRequestDto.getExpirationDate())
                                             .orElse(LocalDateTime.now().plusDays(5)))
                  .userId(urlRequestDto.getUserId())
                  .numberOfClicks(0L)
                  .build();
    }

    private String generateShortKey(Long counterValue){
        return encodingService.encodeValue(counterValue);
    }

    private String generateShortLink(String shortKey) {
        return StringUtils.join(BASE_URL, URL_REDIRECT, shortKey);
    }

    private Long generateUrlId(){
        Sequence sequence = sequenceService.getSequenceById(URL_SEQUENCE_ID);
        return sequence.getCounterValue();
    }

    private UrlResponseDto getUrlResponseDto(Long generateUrlId, String shortKey, Url url) {
        return UrlResponseDto.builder()
                             .urlId(generateUrlId)
                             .originalUrl(url.getOriginalUrl())
                             .shortLink(generateShortLink(shortKey))
                             .numberOfClicks(url.getNumberOfClicks())
                             .expirationDate(url.getExpirationDate())
                             .build();
    }
}
