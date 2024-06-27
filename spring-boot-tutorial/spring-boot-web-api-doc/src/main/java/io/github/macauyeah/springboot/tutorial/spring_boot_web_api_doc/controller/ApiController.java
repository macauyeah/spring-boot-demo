package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_doc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApiController {

    @GetMapping("/api/record/{uuid}")
    public String getRecords(
        @PathVariable("uuid") String uuid,
        @RequestParam String filter) {
        return uuid + filter;
    }
}
