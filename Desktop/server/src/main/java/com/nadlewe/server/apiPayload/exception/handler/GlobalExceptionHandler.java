package com.nadlewe.server.apiPayload.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.nadlewe.server.web.dto.RegisterResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RegisterResponseDTO> handleException(Exception e) {
        RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.BAD_REQUEST.value(), "회원가입 실패");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
