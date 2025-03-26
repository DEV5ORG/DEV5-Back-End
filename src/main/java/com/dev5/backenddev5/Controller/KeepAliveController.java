package com.dev5.backenddev5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/keep-alive")
public class KeepAliveController {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @GetMapping
    public ResponseEntity<String> keepAlive() {
        String formattedDate = LocalDateTime.now().format(FORMATTER);
        System.out.println("Keep-alive recibido " + formattedDate);
        return ResponseEntity.ok("Instancia viva");
    }
}