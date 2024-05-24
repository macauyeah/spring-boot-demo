package io.github.macauyeah.springboot.tutorial.springbootwebapidata.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/someRecord/{uuid}")
    public Map<String, String> readSomeRecord(@PathVariable String uuid) {
        return Map.of("ret", "your uuid:" + uuid);
    }
}
