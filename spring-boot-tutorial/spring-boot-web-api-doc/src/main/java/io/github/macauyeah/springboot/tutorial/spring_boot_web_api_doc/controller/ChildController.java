package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_doc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class ChildController extends ParentController {
    @GetMapping("/direct")
    public String directCall() {
        return "direct result";
    }
}
