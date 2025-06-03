package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_doc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ApiController {
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/record/{uuid}")
    public String getRecords(
            @PathVariable String uuid,
            @RequestParam String filter) {
        LOG.debug("access getRecords");
        return uuid + filter;
    }

    @PostMapping("/api/record")
    public String postDateQuery(@RequestBody ApiDateRequest inputDateRequest) {
        LOG.debug("access postDateQuery");
        return inputDateRequest.getInputDate().toString();
    }
    // public String postDateQuery(@RequestBody String inputDateRequest) {
    // LOG.debug("access postDateQuery:{}", inputDateRequest);
    // return inputDateRequest.toString();
    // }
}
