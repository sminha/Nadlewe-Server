package com.nadlewe.server.web.controller;

import com.nadlewe.server.web.dto.AuthRequestDTO;
import com.nadlewe.server.web.dto.AuthResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/users/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        // [TODO] login

        if ("a@a.com".equals(request.getEmail()) && "1234".equals(request.getPassword())) {
            AuthResponseDTO response = new AuthResponseDTO(HttpStatus.OK.value(), "로그인 성공");
            return ResponseEntity.ok(response);
        } else {
            AuthResponseDTO response = new AuthResponseDTO(HttpStatus.UNAUTHORIZED.value(), "로그인 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    /*@PostMapping("/users/logout")
    public ResponseEntity<AuthResponseDTO> logout(@RequestBody AuthRequestDTO request) {
        // [TODO] logout
    }*/
}
