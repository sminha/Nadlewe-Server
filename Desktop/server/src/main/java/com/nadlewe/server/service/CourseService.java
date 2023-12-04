package com.nadlewe.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadlewe.server.repository.LikesRepository;
import com.nadlewe.server.web.dto.CourseResponseDTO;
import com.nadlewe.server.domain.mappings.Likes;
import com.nadlewe.server.domain.Place;
import com.nadlewe.server.domain.Course;
import com.nadlewe.server.domain.Member;
import com.nadlewe.server.domain.mappings.CoursePlace;
import com.nadlewe.server.repository.CourseRepository;
import com.nadlewe.server.converter.CourseConverter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import static com.nadlewe.server.converter.CourseConverter.convertToCourseEntity;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final LikesRepository likesRepository;

    public CourseService(CourseRepository courseRepository, LikesRepository likesRepository) {
        this.courseRepository = courseRepository;
        this.likesRepository = likesRepository;
    }

    public boolean isCourseExistsWithSameInfo(Course course) {
        // 해당 course와 같은 정보를 가진 course가 있는지 확인하는 로직
        // Course 이름, 가격, 이미지 정보가 동일한지 확인하고,
        // Course에 속한 모든 Place의 정보도 동일한지 확인합니다.

        List<Course> existingCourses = courseRepository.findByNameAndPriceAndImage(course.getName(), course.getPrice(), course.getImage());

        if (existingCourses.isEmpty()) {
            return false; // 동일한 정보를 가진 Course가 존재하지 않습니다.
        }

        for (Course existingCourse : existingCourses) {
            // Course가 이미 존재하면 해당 Course에 속한 Place들도 모두 확인합니다.
            List<Place> existingPlaces = existingCourse.getCoursePlaces().stream()
                    .map(CoursePlace::getPlaceCourse)
                    .collect(Collectors.toList());

            List<Place> newPlaces = course.getCoursePlaces().stream()
                    .map(CoursePlace::getPlaceCourse)
                    .collect(Collectors.toList());

            // Place 목록이 동일한지 확인합니다.
            if (existingPlaces.equals(newPlaces)) {
                return true; // 같은 정보를 가진 Course가 존재합니다.
            }
        }

        return false; // 동일한 정보를 가진 Course가 존재하지 않습니다.
    }

    @Transactional
    public void updateLikeStatus(CourseResponseDTO.Course courseDetail, Long memberId, boolean like) {
        // Convert CourseResponseDTO.Course to Course entity
        Course course = convertToCourseEntity(courseDetail);

        Member member = new Member();
        member.setId(memberId);

        if (!isCourseExistsWithSameInfo(course)) {
            // 동일한 정보를 갖는 Course가 존재하지 않는 경우
            // Course 정보를 저장하고 Likes 테이블에 추가
            Course savedCourse = courseRepository.save(course);

            Likes newLike = Likes.builder()
                    .like(like)
                    .courseMember(savedCourse)
                    .memberCourse(member)
                    .build();

            likesRepository.save(newLike);
        } else {
            // 동일한 정보를 갖는 Course가 이미 존재하는 경우
            Likes likesEntry = likesRepository.findByMemberCourseAndCourseMember(member, course)
                    .orElseThrow(() -> new RuntimeException("Likes entry not found for courseId: " + course.getId() + " and memberId: " + memberId));

            if (like) {
                // 좋아요 추가
                likesEntry.setLike(true);
            } else {
                // 좋아요 취소
                likesRepository.delete(likesEntry);
                courseRepository.delete(course);
            }
        }
    }
}
