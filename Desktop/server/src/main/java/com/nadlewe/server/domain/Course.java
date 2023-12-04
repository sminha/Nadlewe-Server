package com.nadlewe.server.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.nadlewe.server.domain.mappings.CoursePlace;
import com.nadlewe.server.domain.mappings.Likes;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String image;

    // 다대다 관계를 일대다, 다대일로 나누어 매핑
    @OneToMany(mappedBy = "coursePlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePlace> coursePlaces = new ArrayList<>();

    @OneToMany(mappedBy = "courseMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;
}
