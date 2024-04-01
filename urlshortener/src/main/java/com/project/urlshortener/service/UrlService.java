package com.project.urlshortener.service;

import com.project.urlshortener.dto.request.UrlRequestDto;
import com.project.urlshortener.dto.response.UrlResponseDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UrlService {

    UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto);

    void redirectToOriginalUrl(String shortKey, HttpServletResponse response) throws IOException;

    Long getNumberOfClicksByShortKey(String shortKey);
}
