package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import com.kumaran.coursemanagement.mapper.CourseMapper;
import com.kumaran.coursemanagement.repository.CourseRepository;
import com.kumaran.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseMapper mapper;

    @Override
    public CourseResponseDto saveCourse(CourseRequestDto requestDto) {

        Course course = mapper.toEntity(requestDto);

        course.setActive(true);
        course.setTrainerName("Rahul");

        Course savedCourse = repository.save(course);

        return mapper.toResponseDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        List<Course> courses = repository.findAll();

        return courses.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapper.toResponseDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseName(requestDto.getCourseName());
        course.setCourseFee(requestDto.getCourseFee());
        course.setCourseDuration(requestDto.getCourseDuration());
        course.setCourseType(requestDto.getCourseType());

        Course updatedCourse = repository.save(course);

        return mapper.toResponseDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        repository.delete(course);
    }
}