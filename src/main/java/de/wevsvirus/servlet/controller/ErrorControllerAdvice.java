package de.wevsvirus.servlet.controller;

import de.wevsvirus.servlet.controller.data.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {
    private final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public JsonResponse generalError(Exception e) {
        log.error(String.format("Fehler: %s", e.getMessage()), e);
        return JsonResponse.withError("Es ist ein allgemeiner Fehler aufgetreten!");
    }
}
