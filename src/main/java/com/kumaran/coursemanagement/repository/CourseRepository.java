package com.kumaran.coursemanagement.repository;

import com.kumaran.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContainingIgnoreCase(String keyword);

    List<Course> findByActiveTrue();

    Optional<Course> findByIdAndActiveTrue(Long id);

}