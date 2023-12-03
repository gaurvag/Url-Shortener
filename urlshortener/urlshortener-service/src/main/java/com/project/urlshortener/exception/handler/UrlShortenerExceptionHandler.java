package com.project.urlshortener.exception.handler;

import com.project.urlshortener.exception.UrlExpiredException;
import com.project.urlshortener.exception.UrlNotFoundException;
import com.project.urlshortener.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.project.urlshortener.util.ResponseEntityBuilder.DEBUG_MESSAGE;
import static com.project.urlshortener.util.ResponseEntityBuilder.errorResponseEntity;

@RestControllerAdvice
public class UrlShortenerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleException(MethodArgumentNotValidException ex){
        Map<String, String> errorDetails = new HashMap<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error -> errorDetails.put(error.getField(), error.getDefaultMessage()));
        return errorResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception ) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(DEBUG_MESSAGE, exception.getMessage());
        return errorResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String,Object>> handleValidationException(
            ValidationException exception ) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(DEBUG_MESSAGE, exception.getMessage());
        return errorResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<Map<String,Object>> handleUrlExpiredException(
            UrlExpiredException exception ) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(DEBUG_MESSAGE, exception.getMessage());
        return errorResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUrlNotFoundException(
            UrlNotFoundException exception ) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(DEBUG_MESSAGE, exception.getMessage());
        return errorResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

}
