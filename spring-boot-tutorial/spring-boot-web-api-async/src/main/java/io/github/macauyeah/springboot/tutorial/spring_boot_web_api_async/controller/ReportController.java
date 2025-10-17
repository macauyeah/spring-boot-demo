package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_async.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.macauyeah.springboot.tutorial.spring_boot_web_api_async.service.ReportService;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ReportController {
    private final Map<String, String> orderStatus = new ConcurrentHashMap<>();
    public static final String PROCESSING = "Processing";
    public static final String COMPLETED = "Completed";
    @Autowired
    private ReportService reportService;

    @PostMapping("/reportJob/create")
    public ResponseEntity<Object> createJob() {
        String uuid = String.format("%d_%s", (new Date()).getTime(), UUID.randomUUID().toString());
        CompletableFuture.runAsync(() -> {
            try {
                orderStatus.put(uuid, PROCESSING);
                Thread.sleep(10000); // 10-second simulated delay
                reportService.genAndSaveReport(uuid);
                orderStatus.put(uuid, COMPLETED);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return ResponseEntity
                .accepted()
                .header(HttpHeaders.LOCATION, "/reportJob/status/" + uuid)
                .body(Map.of("uuid", uuid, "status api",
                        "/api/reportJob/status/" + uuid, "download api",
                        "/api/reportJob/download/" + uuid));
    }

    @GetMapping("/reportJob/status/{uuid}")
    public ResponseEntity<Object> getStatus(@PathVariable("uuid") String uuid) {
        String status = orderStatus.get(uuid);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }

        if (COMPLETED.equals(status)) {
            // return ResponseEntity.status(HttpStatus.SEE_OTHER)
            return ResponseEntity.ok()
                    .header(HttpHeaders.LOCATION, "/api/reportJob/download/" + uuid)
                    .body(Map.of("status", COMPLETED));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("status", PROCESSING));
    }

    @GetMapping("/reportJob/download/{uuid}")
    public ResponseEntity<Resource> download(@PathVariable("uuid") String uuid) {
        String status = orderStatus.get(uuid);
        if (status == null || !COMPLETED.equals(status)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource;
        try {
            resource = new InputStreamResource(reportService.getReport(uuid));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"Report-" + uuid + ".txt\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
