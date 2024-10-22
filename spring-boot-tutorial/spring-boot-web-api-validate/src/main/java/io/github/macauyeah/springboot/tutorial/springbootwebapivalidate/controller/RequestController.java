package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
public class RequestController {
    @PostMapping("/api/postSomething")
    public Map<String, Object> postMethodName(
            @RequestBody @Valid FirstLevel entity) {
        return Map.of("ret", entity, "date", new Date());
    }
}
