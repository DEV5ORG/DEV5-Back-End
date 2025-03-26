package com.dev5.backenddev5.Job;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveJob {
    private final RestTemplate restTemplate = new RestTemplate();
    // Este job solo es útil para el recurso público. URL hardcodeada para mayor facilidad.
    private static final String KEEP_ALIVE_URL = "https://dev5-back-end.onrender.com/api/keep-alive";

    @Scheduled(fixedRate = 600_000)
    public void callKeepAliveEndpoint() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(KEEP_ALIVE_URL, String.class);
            System.out.println("Respuesta del keep-alive: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error en el job keep-alive: " + e.getMessage());
        }
    }
}
