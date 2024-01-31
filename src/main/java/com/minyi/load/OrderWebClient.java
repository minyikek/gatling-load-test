package com.minyi.load;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderWebClient {

    private final WebClient webClient;

    public OrderWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:10010").build();
    }

    public Mono<String> create() {
        return this.webClient.post()
                .uri("/create")
                .retrieve()
                .bodyToMono(String.class);
    }
}
