package de.wevsvirus.servlet.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {
    private final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalError(Exception e) {
        final String bodyOfResponse = String.format("Fehler: %s", e.getMessage());
        log.error(bodyOfResponse, e);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
