package com.nadlewe.server.web.controller;

import java.util.List;
import java.util.ArrayList;

import com.nadlewe.server.web.dto.CourseRequestDTO;
import com.nadlewe.server.web.dto.CourseResponseDTO;
import com.nadlewe.server.web.dto.LikesRequestDTO;
import com.nadlewe.server.web.dto.LikesResponseDTO;
import com.nadlewe.server.service.RecommendationService;
import com.nadlewe.server.service.CourseService;

import com.nadlewe.server.web.dto.LikesResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CourseController {

    // for test
    private CourseResponseDTO testResponse;

    @PostMapping("/courses")
    // public ResponseEntity<Mono<CourseResponseDTO>> recommendCourse(@RequestBody CourseRequestDTO request) {
    public CourseResponseDTO recommendCourse(@RequestBody CourseRequestDTO request) {

        // 1. front->back
        // {"tagKeys":["식사","식사","카페","활동"],"selectedCategory":["와인","칵테일","방탈출"],"priceRange":[70000,130000]}
        System.out.println(request.toString());

        // 2. back->fastAPI
        // {"minPrice":70000,"maxPrice":130000,"themes":["식사","식사","카페","활동"],"kinds":["와인","칵테일","방탈출"]}
        // for actual logic
        // Mono<CourseResponseDTO> response = RecommendationService.sendRecommendationRequest(request);
        // like 처리
        
        // for test
        testResponse = new CourseResponseDTO();
        List<CourseResponseDTO.Course> courses = new ArrayList<>();

        // 첫 번째 코스
        CourseResponseDTO.Course course1 = new CourseResponseDTO.Course();
        course1.setCourseName("저녁 야경 데이트");
        course1.setCoursePrice(87000);
        course1.setCourseImage("코스1 이미지 파일 경로");

        List<CourseResponseDTO.Place> places1 = new ArrayList<>();

        CourseResponseDTO.Place place1_1 = new CourseResponseDTO.Place();
        place1_1.setPlaceName("박지후 스시");
        place1_1.setRate(5);
        place1_1.setMenu("2인 SET");
        // place1_1.setMenuDetail("스시2인세트, 샐러드, 초밥20pcs, 간장새우, 새우튀김, 뚝배기, 우동");
        place1_1.setPlacePrice(39000);
        place1_1.setPlaceImage("장소1 이미지 파일 경로");

        CourseResponseDTO.Place place1_2 = new CourseResponseDTO.Place();
        place1_2.setPlaceName("남산타워");
        place1_2.setRate(3);
        place1_2.setMenu("메뉴2");
        // place1_2.setMenuDetail("메뉴 상세 설명2");
        place1_2.setPlacePrice(42000);
        place1_2.setPlaceImage("장소2 이미지 파일 경로");

        CourseResponseDTO.Place place1_3 = new CourseResponseDTO.Place();
        place1_3.setPlaceName("오뤼르 베이커리");
        place1_3.setRate(4);
        place1_3.setMenu("겨울 SET");
        // place1_3.setMenuDetail("아메리카노, 따뜻한 우유, 크루와상 2ea");
        place1_3.setPlacePrice(17000);
        place1_3.setPlaceImage("장소2 이미지 파일 경로");

        places1.add(place1_1);
        places1.add(place1_2);
        places1.add(place1_3);

        course1.setPlaces(places1);

        courses.add(course1);

        // 두 번째 코스
        CourseResponseDTO.Course course2 = new CourseResponseDTO.Course();
        course2.setCourseName("동대입구역 데이트");
        course2.setCoursePrice(22000);
        course2.setCourseImage("코스2 이미지 파일 경로");

        List<CourseResponseDTO.Place> places2 = new ArrayList<>();

        CourseResponseDTO.Place place2_1 = new CourseResponseDTO.Place();
        place2_1.setPlaceName("장충단 공원");
        place2_1.setRate(3.5);
        place2_1.setMenu("메뉴3");
        // place2_1.setMenuDetail("메뉴 상세 설명3");
        place2_1.setPlacePrice(7000);
        place2_1.setPlaceImage("장소3 이미지 파일 경로");

        CourseResponseDTO.Place place2_2 = new CourseResponseDTO.Place();
        place2_2.setPlaceName("태극당");
        place2_2.setRate(4.5);
        place2_2.setMenu("브런치 SET");
        // place2_2.setMenuDetail("닭가슴살 샐러드, 모나카 2개");
        place2_2.setPlacePrice(15000);
        place2_2.setPlaceImage("장소4 이미지 파일 경로");

        places2.add(place2_1);
        places2.add(place2_2);

        course2.setPlaces(places2);

        courses.add(course2);

        testResponse.setCourses(courses);

        // 3. fastAPI->back
        // for actual logic
        // return ResponseEntity.ok(response);

        // for test
        System.out.println(testResponse.toString());
        return testResponse;
    }

    @GetMapping("/courses/{courseIndex}")
    public ResponseEntity<CourseResponseDTO.Course> getCourseDetail(@PathVariable int courseIndex) {
        CourseResponseDTO.Course courseDetail = getCourseDetailByIndex(courseIndex);

        System.out.println(courseDetail.toString());

        if (courseDetail != null) {
            return ResponseEntity.ok(courseDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private CourseResponseDTO.Course getCourseDetailByIndex(int courseIndex) {
        CourseResponseDTO.Course courseDetail = null;

        if (courseIndex >= 0 && courseIndex < testResponse.getCourses().size()) {
            courseDetail = testResponse.getCourses().get(courseIndex);
        }

        return courseDetail;
    }

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{courseIndex}/like")
    public ResponseEntity<LikesResponseDTO> likeCourse(@PathVariable int courseIndex, @RequestBody LikesRequestDTO likesRequestDTO) {
        try {
            // 기존의 courseId 대신 courseIndex를 사용하여 해당 Course를 가져옴
            CourseResponseDTO.Course courseDetail = getCourseDetailByIndex(courseIndex);

            System.out.println("좋아요한 애");
            System.out.println(courseDetail.toString());

            if (courseDetail != null) {
                courseService.updateLikeStatus(courseDetail, likesRequestDTO.getMemberId(), likesRequestDTO.isLike());
                return ResponseEntity.ok(new LikesResponseDTO(200, "좋아요 변경 성공"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new LikesResponseDTO(500, "좋아요 변경 실패"));
        }
    }

}