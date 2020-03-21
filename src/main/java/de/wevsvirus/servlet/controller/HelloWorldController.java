package de.wevsvirus.servlet.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {


    @ApiOperation(value = "Greets with hello world")
    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String GetCurrentUser() {
        return "hello world";
    }
}
