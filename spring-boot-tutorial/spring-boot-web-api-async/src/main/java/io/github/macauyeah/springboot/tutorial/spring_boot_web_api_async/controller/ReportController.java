package io.github.macauyeah.springboot.tutorial.spring_boot_web_api_async.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ReportController {
    private final Map<String, String> orderStatus = new ConcurrentHashMap<>();

    @PostMapping("/reportJob/create/{uuid}")
    public ResponseEntity<Void> createJob(@PathVariable("uuid") String uuid) {
        // Simulate long task
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000); // 10-second simulated delay
                orderStatus.put(uuid, "Completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return ResponseEntity
                .accepted()
                .header(HttpHeaders.LOCATION, "/reportJob/status/" + uuid)
                .build();
    }

    @GetMapping("/reportJob/status/{uuid}")
    public ResponseEntity<Object> getStatus(@PathVariable("uuid") String uuid) {
        String status = orderStatus.get(uuid);
        if (status == null) return ResponseEntity.notFound().build();

        if ("Completed".equals(status)) {
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                    .header(HttpHeaders.LOCATION, "/api/reportJob/download/" + uuid)
                    .body(Map.of("status", "Completed"));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("status", "Processing"));
    }

    @GetMapping("reportJob/download/{uuid}")
    public ResponseEntity<String> getFinalOrder(@PathVariable("uuid") String uuid) {
        if (!orderStatus.containsKey(uuid)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Order " + uuid + " is ready!");
    }

}
