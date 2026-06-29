package com.kumaran.coursemanagement.mapper;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto requestDto) {

        Course course = new Course();

        course.setCourseName(requestDto.getCourseName());
        course.setCourseFee(requestDto.getCourseFee());
        course.setCourseDuration(requestDto.getCourseDuration());
        course.setCourseType(requestDto.getCourseType());

        return course;
    }

    public CourseResponseDto toResponseDto(Course course) {

        CourseResponseDto responseDto = new CourseResponseDto();

        responseDto.setId(course.getId());
        responseDto.setCourseName(course.getCourseName());
        responseDto.setCourseFee(course.getCourseFee());
        responseDto.setCourseDuration(course.getCourseDuration());
        responseDto.setCourseType(course.getCourseType());
        responseDto.setTrainerName(course.getTrainerName());
        responseDto.setActive(course.isActive());
        responseDto.setCreatedAt(course.getCreatedAt());
        responseDto.setUpdatedAt(course.getUpdatedAt());

        return responseDto;
    }
}