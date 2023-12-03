package com.project.urlshortener.service;

public interface EncodingService {
    String encodeValue(Long value);
    char encodeBase10ToCharInBase(Long value);
}
