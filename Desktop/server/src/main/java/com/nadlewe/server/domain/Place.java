package com.nadlewe.server.domain;

import lombok .*;

import javax.persistence .*;
import java.util.ArrayList;
import java.util.List;

import com.nadlewe.server.domain.mappings.CoursePlace;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private String menu;

    // 다대다 관계를 일대다, 다대일로 나누어 매핑
    @OneToMany(mappedBy = "placeCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePlace> placeCourses = new ArrayList<>();
}