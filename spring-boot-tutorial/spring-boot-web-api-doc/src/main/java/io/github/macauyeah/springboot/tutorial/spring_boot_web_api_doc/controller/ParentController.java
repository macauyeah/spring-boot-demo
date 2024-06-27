package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_doc.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ParentController {
    @GetMapping("/postfix")
    public String postfix(){
        return "this is postfix";
    }
    
}
