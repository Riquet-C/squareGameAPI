package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserValidationService {

    RestClient restClient;

    public UserValidationService() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:8081/users").build();
    }

    public String validateUser(int userId) {
        return restClient.get()
                .uri("/{userId}", userId)
                .header("X-userId", String.valueOf(userId))
                .retrieve()
                .body(String.class);
    }
}
