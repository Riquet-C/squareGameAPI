package com.example.demo.model;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    private final SecureRandom random = new SecureRandom();

    @Override
    public int get() {
        return random.nextInt(100);
    }
}
