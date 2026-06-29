package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import com.kumaran.coursemanagement.exception.ResourceNotFoundException;
import com.kumaran.coursemanagement.mapper.CourseMapper;
import com.kumaran.coursemanagement.repository.CourseRepository;
import com.kumaran.coursemanagement.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    public CourseServiceImpl(CourseRepository repository, CourseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CourseResponseDto saveCourse(CourseRequestDto requestDto) {

        log.info("Creating new course : {}", requestDto.getCourseName());

        Course course = mapper.toEntity(requestDto);

        course.setTrainerName("Rahul");
        course.setActive(true);

        Course savedCourse = repository.save(course);

        log.info("Course created successfully with id : {}", savedCourse.getId());

        return mapper.toResponseDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        log.info("Fetching all active courses");

        List<Course> courses = repository.findByActiveTrue();

        return courses.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {

        log.info("Fetching course with id : {}", id);

        Course course = repository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        return mapper.toResponseDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) {

        log.info("Updating course with id : {}", id);

        Course course = repository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        course.setCourseName(requestDto.getCourseName());
        course.setCourseFee(requestDto.getCourseFee());
        course.setCourseDuration(requestDto.getCourseDuration());
        course.setCourseType(requestDto.getCourseType());

        Course updatedCourse = repository.save(course);

        log.info("Course updated successfully");

        return mapper.toResponseDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        log.info("Soft deleting course : {}", id);

        Course course = repository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        course.setActive(false);

        repository.save(course);

        log.info("Course soft deleted successfully");
    }

    @Override
    public List<CourseResponseDto> getCoursesWithPagination(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage = repository.findAll(pageable);

        return coursePage.getContent()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public List<CourseResponseDto> searchCourses(String keyword) {

        log.info("Searching courses : {}", keyword);

        List<Course> courses =
                repository.findByCourseNameContainingIgnoreCase(keyword);

        return courses.stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}