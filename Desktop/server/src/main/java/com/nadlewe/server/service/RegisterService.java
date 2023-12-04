// RegisterService.java
package com.nadlewe.server.service;

import com.nadlewe.server.domain.Member;
import com.nadlewe.server.domain.enums.Gender;
import com.nadlewe.server.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final MemberRepository memberRepository;

    public RegisterService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(String name, int age, String gender, String email, String password, String phone) {
        if (memberRepository.existsByEmail(email)) {
            // Handle existing email
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member newMember = Member.builder()
                .name(name)
                .age(age)
                .gender(Gender.valueOf(gender.toUpperCase())) // Assuming Gender is an enum
                .email(email)
                .password(password)
                .phone(phone)
                .build();

        memberRepository.save(newMember);
    }
}
