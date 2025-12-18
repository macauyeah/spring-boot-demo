package io.github.macauyeah.springboot.tutorial.fast_excel.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.idev.excel.FastExcel;
import io.github.macauyeah.springboot.tutorial.fast_excel.excel_dto.DemoData;
import io.github.macauyeah.springboot.tutorial.fast_excel.listener.DemoDataListener;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload!");
        }

        try {
            FastExcel.read(file.getInputStream(), DemoData.class, new DemoDataListener())
                    .sheet()
                    .doRead();
            return ResponseEntity.ok("File uploaded and processed successfully!");
        } catch (IOException e) {
            // log.error("File processing failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing failed");
        }
    }
}