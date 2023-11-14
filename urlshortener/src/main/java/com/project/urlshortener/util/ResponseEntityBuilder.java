package com.project.urlshortener.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityBuilder {
    public static final String STATUS = "status";
    public static final String FAILED = "failed";
    public static final String SUCCESS = "success";
    public static final String ERROR_DETAILS = "errorDetails";
    public static final String DATA = "data";
    public static final String DEBUG_MESSAGE = "debugMessage";

    public static ResponseEntity<Map<String, Object>> okResponseEntity(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, SUCCESS);
        response.put(DATA, data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> errorResponseEntity(Object errorDetails, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, FAILED);
        response.put(ERROR_DETAILS, errorDetails);
        return ResponseEntity.status(httpStatus).body(response);
    }

}
