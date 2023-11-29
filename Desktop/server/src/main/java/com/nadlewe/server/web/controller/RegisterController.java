package com.nadlewe.server.web.controller;

import com.nadlewe.server.web.dto.RegisterRequestDTO;
import com.nadlewe.server.web.dto.RegisterResponseDTO;

import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Validated
public class RegisterController {

    @PostMapping("/users")
    public ResponseEntity<Mono<RegisterResponseDTO>> register(@RequestBody RegisterRequestDTO request) {
        try {
            // [TODO] register

            System.out.println(request.toString());
            RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.OK.value(), "회원가입 성공");
            return ResponseEntity.ok(Mono.just(response));
        } catch (Exception e) {
            // [TODO] register exception

            RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.BAD_REQUEST.value(), "회원가입 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(response));
        }
    }
}