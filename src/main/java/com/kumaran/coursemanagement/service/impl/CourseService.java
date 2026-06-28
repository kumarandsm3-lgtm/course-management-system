package com.kumaran.coursemanagement.service;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto saveCourse(CourseRequestDto requestDto);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto getCourseById(Long id);

    CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto);

    void deleteCourse(Long id);
}