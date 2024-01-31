package com.minyi.load.rest;

import com.minyi.load.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> create() {
        // Start the asynchronous operation
        CompletableFuture<String> asyncResult = orderService.create();

        // Return the CompletableFuture immediately
        return asyncResult.thenApply(result ->
                ResponseEntity.ok().body(result)
        );
    }
}
