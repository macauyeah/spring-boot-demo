package io.github.macauyeah.springboot.tutorial.spring_boot_rest_template_test.config;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    HttpClient getHttpClient() {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        return client;
    }
}
