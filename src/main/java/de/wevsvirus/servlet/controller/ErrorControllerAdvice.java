package de.wevsvirus.servlet.controller;

import de.wevsvirus.servlet.controller.data.JsonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(Exception.class)
    public JsonResponse generalError() {
        JsonResponse resp = new JsonResponse();
        resp.setSuccess(false);
        resp.setError("Es ist ein allgemeiner Fehler aufgetreten!");
        return resp;
    }
}
