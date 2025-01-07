package io.github.macauyeah.springboot.tutorial.spring_boot_rest_template_test.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProxyPassController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpClient httpClient;

    @GetMapping("/api/searchEngine/{query}")
    public Map<String, String> searchEngine(@PathVariable(value = "query") String query) {
        String googleHtml = restTemplate.getForObject("https://www.google.com/search?=" + query, String.class);
        return Map.of("ret", googleHtml);
    }

    @GetMapping("/api/rawClientSearchEngine/{query}")
    public Map<String, String> searchEngineByRawClient(@PathVariable(value = "query") String query) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(
						URI.create("https://www.google.com/search?=" + query))
				.timeout(Duration.ofMinutes(2))
				.GET()
				.build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Map.of("ret", response.body());

    }
}
