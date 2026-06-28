package com.kumaran.coursemanagement.mapper;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    // RequestDTO -> Entity
    public Course toEntity(CourseRequestDto dto) {

        Course course = new Course();

        course.setCourseName(dto.getCourseName());
        course.setCourseFee(dto.getCourseFee());
        course.setCourseDuration(dto.getCourseDuration());
        course.setCourseType(dto.getCourseType());

        return course;
    }

    // Entity -> ResponseDTO
    public CourseResponseDto toResponseDto(Course course) {

        CourseResponseDto response = new CourseResponseDto();

        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setCourseFee(course.getCourseFee());
        response.setCourseDuration(course.getCourseDuration());
        response.setCourseType(course.getCourseType());
        response.setTrainerName(course.getTrainerName());
        response.setActive(course.isActive());

        return response;
    }
}