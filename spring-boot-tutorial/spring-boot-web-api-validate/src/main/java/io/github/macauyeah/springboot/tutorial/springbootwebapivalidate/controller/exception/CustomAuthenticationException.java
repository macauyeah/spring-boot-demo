package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException() {
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
