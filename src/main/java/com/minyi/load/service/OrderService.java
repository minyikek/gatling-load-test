package com.minyi.load.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    @PostMapping
    public CompletableFuture<String> create() {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate async processing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "SUCCESS";
        });
    }
}
