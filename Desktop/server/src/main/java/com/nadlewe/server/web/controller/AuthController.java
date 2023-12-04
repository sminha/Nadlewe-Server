package com.nadlewe.server.web.controller;

import com.nadlewe.server.service.AuthService;
import com.nadlewe.server.web.dto.AuthRequestDTO;
import com.nadlewe.server.web.dto.AuthResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request, HttpServletRequest httpServletRequest) {
        String email = request.getEmail();
        String password = request.getPassword();

        boolean authenticated = authService.authenticate(email, password);

        if (authenticated) {
            HttpSession session = httpServletRequest.getSession(true);
            // 세션에 필요한 정보 설정
            session.setAttribute("email", email);
            Long userId = authService.getUserIdByEmail(email);
            return ResponseEntity.ok(new AuthResponseDTO(HttpStatus.OK.value(), "로그인 성공", userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponseDTO(HttpStatus.UNAUTHORIZED.value(), "로그인 실패", null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponseDTO> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(new AuthResponseDTO(HttpStatus.OK.value(), "로그아웃 성공", null));
    }
}
