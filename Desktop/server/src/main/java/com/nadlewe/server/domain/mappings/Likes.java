package com.nadlewe.server.domain.mappings;

import lombok.*;

import javax.persistence.*;

import com.nadlewe.server.domain.Member;
import com.nadlewe.server.domain.Course;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean like;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberCourse;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseMember;
}