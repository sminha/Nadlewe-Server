package com.nadlewe.server.web.dto;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class RegisterRequestDTO {
    /*private String name;
    private int age;
    private String gender;
    private String email;
    private String password;
    private String phone;*/

    @NotNull(message = "이름은 비워둘 수 없습니다.")
    private String name;

    private int age;

    @NotNull(message = "성별은 비워둘 수 없습니다.")
    private String gender;

    @NotNull(message = "이메일은 비워둘 수 없습니다.")
    private String email;

    @NotNull(message = "비밀번호는 비워둘 수 없습니다.")
    private String password;

    @NotNull(message = "전화번호는 비워둘 수 없습니다.")
    private String phone;

    // for postman test
    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}