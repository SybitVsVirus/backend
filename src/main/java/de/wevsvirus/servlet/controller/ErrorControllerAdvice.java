package de.wevsvirus.servlet.controller;

import de.wevsvirus.servlet.controller.data.JsonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    public JsonResponse generalError() {
        return JsonResponse.withError("Es ist ein allgemeiner Fehler aufgetreten!");
    }
}
