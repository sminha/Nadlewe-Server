package com.nadlewe.server.domain;

import java.util.List;

import javax.persistence.*;
import lombok.*;

import com.nadlewe.server.domain.enums.Gender;
import com.nadlewe.server.domain.mappings.Likes;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    private Gender gender;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 15)
    private String phone;

    @OneToMany(mappedBy = "memberCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;
}
