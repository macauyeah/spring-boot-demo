package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import org.springframework.web.bind.annotation.RestController;

import io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller.exception.CustomAuthenticationException;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class RequestController {
    @PostMapping("/api/postSomething")
    public Map<String, Object> postMethodName(
            @RequestBody @Valid FirstLevel entity) {
        return Map.of("ret", entity, "date", new Date());
    }

    @GetMapping("/api/forceError")
    public String forceRuntimeException() {
        throw new RuntimeException("force runtime error");
    }

    @GetMapping("/api/ioError")
    public String forceIOException() throws IOException {
        throw new IOException("force io error");
    }

    @GetMapping("/api/authError")
    public String forceAuthException() throws CustomAuthenticationException {
        throw new CustomAuthenticationException("custom auth error");
    }
}
