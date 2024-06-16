package io.github.macauyeah.springboot.tutorial.springbootwebapidata.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Value("${project.version}")
    private String projectVersion;

    @Value("${git.build.version}")
    private String gitBuildVersion;

    @GetMapping("/someRecord/{uuid}")
    public Map<String, String> readSomeRecord(@PathVariable String uuid) {
        return Map.of("ret", "your uuid:" + uuid);
    }

    @GetMapping("/version")
    public Map<String, String> getVersion() {
        return Map.of("projectVersion", projectVersion, "gitBuildVersion",
                gitBuildVersion);
    }
}
