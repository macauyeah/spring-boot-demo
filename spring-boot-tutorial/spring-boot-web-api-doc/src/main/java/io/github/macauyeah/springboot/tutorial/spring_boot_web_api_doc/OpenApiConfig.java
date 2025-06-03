package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_doc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9000/proxy/8080/");
        // and paste http://localhost:9000/proxy/8080/v3/api-docs to the swagger-ui "explore" 
        return new OpenAPI().servers(List.of(server));
    }

}
