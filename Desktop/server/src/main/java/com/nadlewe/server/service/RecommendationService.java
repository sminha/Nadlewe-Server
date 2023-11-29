package com.nadlewe.server.service;

import com.nadlewe.server.web.dto.CourseRequestDTO;
import com.nadlewe.server.web.dto.CourseResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RecommendationService {

    private static WebClient webClient;

    @Autowired
    public RecommendationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://fastapi-server:8000").build();
    }

    public static Mono<CourseResponseDTO> sendRecommendationRequest(CourseRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // fastAPI로 검색 옵션 전달
        Mono<CourseResponseDTO> response = webClient.post()
                .uri("/recommendation")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(CourseResponseDTO.class);

        return response;
    }
}