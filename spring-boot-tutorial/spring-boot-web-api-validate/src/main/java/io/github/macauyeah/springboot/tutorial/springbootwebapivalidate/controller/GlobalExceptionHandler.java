package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller.exception.CustomAuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // cannot catch the top most java.lang.Exception, because all spring boot
    // defined error will be truned into status 500
    // TODO another problem, how to report exception that not inherit from
    // RuntimeException, like IOException throw from controller
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception ex) {
        // do any other logic here, eg send alert, special logging.
        return Map.of("ret", false, "message", "masked message");
    }

    @ExceptionHandler(value = CustomAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleCustAuthException(Exception ex) {
        // do any other logic here, eg send alert, special logging.
        return Map.of("ret", false, "message", ex.getMessage());
    }
}
