package com.nadlewe.server.repository;

import com.nadlewe.server.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // 예시: 이름(name), 가격(price), 이미지(image)로 Course 찾기
    List<Course> findByNameAndPriceAndImage(String name, int price, String image);
}