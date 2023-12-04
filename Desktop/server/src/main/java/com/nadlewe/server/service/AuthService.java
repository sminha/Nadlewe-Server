package com.nadlewe.server.service;

import com.nadlewe.server.domain.Member;
import com.nadlewe.server.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean authenticate(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password);
        return member != null;
    }

    public Long getUserIdByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member != null ? member.getId() : null;
    }
}
