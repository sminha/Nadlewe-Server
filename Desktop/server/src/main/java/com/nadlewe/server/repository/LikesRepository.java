package com.nadlewe.server.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nadlewe.server.domain.mappings.Likes;
import com.nadlewe.server.domain.Member;
import com.nadlewe.server.domain.Course;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemberCourseAndCourseMember(Member member, Course course);

    @Transactional
    @Modifying
    @Query("UPDATE Likes l SET l.like = :like WHERE l.courseMember.id = :courseId AND l.memberCourse.id = :memberId")
    void updateLikeStatus(@Param("courseId") Long courseId, @Param("memberId") Long memberId, @Param("like") boolean like);
}
