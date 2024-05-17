package io.github.macauyeah.springboot.tutorial.springbootwebapibasic.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/someRecord/{uuid}")
    public Map<String, String> readSomeRecord(@PathVariable String uuid) {
        return Map.of("ret", "your uuid:" + uuid);
    }

    @PostMapping("/someRecord")
    public Map<String, String> createSomeRecord(@RequestBody Map<String, String> requestBody) {
        HashMap<String, String> ret = new HashMap<>(requestBody);
        ret.put("ret", "got your request");
        return ret;
    }
}
