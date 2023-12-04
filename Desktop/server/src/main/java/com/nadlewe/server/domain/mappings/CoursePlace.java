package com.nadlewe.server.domain.mappings;

import lombok.*;

import javax.persistence.*;

import com.nadlewe.server.domain.Course;
import com.nadlewe.server.domain.Place;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course coursePlace;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place placeCourse;
}