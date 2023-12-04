package com.nadlewe.server.converter;

import com.nadlewe.server.domain.Course;
import com.nadlewe.server.domain.mappings.CoursePlace;
import com.nadlewe.server.web.dto.CourseResponseDTO;

import java.util.stream.Collectors;
import java.util.List;

public class CourseConverter {

    public static Course convertToCourseEntity(CourseResponseDTO.Course courseDetail) {
        Course course = new Course();
        // course.setId(courseDetail.getId());
        course.setName(courseDetail.getCourseName());
        course.setPrice(courseDetail.getCoursePrice());
        course.setImage(courseDetail.getCourseImage());

        // Convert CourseResponseDTO.Place to CoursePlace entity and set it to the course
        List<CoursePlace> coursePlaces = courseDetail.getPlaces().stream()
                .map(place -> {
                    CoursePlace coursePlace = new CoursePlace();
                    coursePlace.setCoursePlace(course);
                    // Set other properties for CoursePlace entity
                    return coursePlace;
                })
                .collect(Collectors.toList());

        course.setCoursePlaces(coursePlaces);

        return course;
    }
}
