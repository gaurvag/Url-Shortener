package com.project.urlshortener.api;

import com.project.urlshortener.dto.request.UrlRequestDto;
import com.project.urlshortener.dto.response.UrlResponseDto;
import com.project.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static com.project.urlshortener.util.ResponseEntityBuilder.okResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url")
public class UrlApi {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String,Object>> generateShortLink(@RequestBody @Valid UrlRequestDto urlRequestDto) {
        return okResponseEntity(urlService.shortenUrl(urlRequestDto));
    }

    @GetMapping("/redirect/{shortKey}")
    public ResponseEntity<Map<String,Object>> redirectToOriginalUrl(@PathVariable String shortKey, HttpServletResponse response) throws IOException {
        urlService.redirectToOriginalUrl(shortKey, response);
        return okResponseEntity("done");
    }

}
