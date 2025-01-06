package io.github.macauyeah.springboot.tutorial.sprint_boot_rest_template_test.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProxyPassController {
    @GetMapping("/api/searchEngine/{query}")
    public Map<String, String> searchEngine(@PathVariable(value = "query") String query) {
        // TODO proxy pass query to backend search engine and return search engine result to client; 
        return Map.of("ret", query);
    }
    
}
