package org.example.deskbooker.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class NotifyController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://notification-service:5000/notify";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"user\": \"Jana\", \"action\": \"register\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.ok("User registered & notification sent: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace(); // optional for debug
            return ResponseEntity.status(500).body("Error contacting notification service: " + e.getMessage());
        }
    }
}