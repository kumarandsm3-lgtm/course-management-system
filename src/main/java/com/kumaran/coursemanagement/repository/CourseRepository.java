package com.kumaran.coursemanagement.repository;

import com.kumaran.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContainingIgnoreCase(String keyword);

}