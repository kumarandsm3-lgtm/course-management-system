package com.kumaran.coursemanagement.repository;

import com.kumaran.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}