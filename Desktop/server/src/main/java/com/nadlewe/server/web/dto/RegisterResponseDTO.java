package com.nadlewe.server.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDTO {
     private int code;
     private String message;

     public RegisterResponseDTO(int code, String message) {
         this.code = code;
         this.message = message;
     }
}