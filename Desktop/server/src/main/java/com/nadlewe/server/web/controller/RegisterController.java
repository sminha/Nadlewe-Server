package com.nadlewe.server.web.controller;

import com.nadlewe.server.domain.Member;
import com.nadlewe.server.domain.enums.Gender;
import com.nadlewe.server.web.dto.RegisterRequestDTO;
import com.nadlewe.server.web.dto.RegisterResponseDTO;
import com.nadlewe.server.repository.MemberRepository;

import javax.validation.Valid;
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

    private final MemberRepository memberRepository;

    public RegisterController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Mono<RegisterResponseDTO>> register(@RequestBody @Valid RegisterRequestDTO request) {
        try {
            if (memberRepository.existsByEmail(request.getEmail())) {
                RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(response));
            }

            Member newMember = Member.builder()
                    .name(request.getName())
                    .age(request.getAge())
                    .gender(Gender.valueOf(request.getGender().toUpperCase())) // Assuming Gender is an enum
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .phone(request.getPhone())
                    .build();

            memberRepository.save(newMember);

            RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.OK.value(), "회원가입 성공");
            return ResponseEntity.ok(Mono.just(response));
        } catch (Exception e) {
            RegisterResponseDTO response = new RegisterResponseDTO(HttpStatus.BAD_REQUEST.value(), "회원가입 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(response));
        }
    }
}