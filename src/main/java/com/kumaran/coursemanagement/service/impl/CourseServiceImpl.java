package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import com.kumaran.coursemanagement.exception.ResourceNotFoundException;
import com.kumaran.coursemanagement.mapper.CourseMapper;
import com.kumaran.coursemanagement.repository.CourseRepository;
import com.kumaran.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        course.setTrainerName("Rahul");
        course.setActive(true);

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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        return mapper.toResponseDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) {

        Course course = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        repository.delete(course);
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

        List<Course> courses =
                repository.findByCourseNameContainingIgnoreCase(keyword);

        return courses.stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}