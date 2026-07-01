package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import com.kumaran.coursemanagement.mapper.CourseMapper;
import com.kumaran.coursemanagement.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private CourseMapper mapper;

    @InjectMocks
    private CourseServiceImpl service;

    @Test
    void saveCourse_shouldReturnCourseResponse() {

        // Request DTO
        CourseRequestDto requestDto = new CourseRequestDto();
        requestDto.setCourseName("Java Backend Development");
        requestDto.setCourseFee(15000.0);
        requestDto.setCourseDuration(4);
        requestDto.setCourseType("Online");

        // Entity before save
        Course course = new Course();
        course.setCourseName("Java Backend Development");
        course.setCourseFee(15000.0);
        course.setCourseDuration(4);
        course.setCourseType("Online");

        // Entity after save
        Course savedCourse = new Course();
        savedCourse.setId(1L);
        savedCourse.setCourseName("Java Backend Development");
        savedCourse.setCourseFee(15000.0);
        savedCourse.setCourseDuration(4);
        savedCourse.setCourseType("Online");
        savedCourse.setTrainerName("Rahul");
        savedCourse.setActive(true);

        // Response DTO
        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(1L);
        responseDto.setCourseName("Java Backend Development");
        responseDto.setCourseFee(15000.0);
        responseDto.setCourseDuration(4);
        responseDto.setCourseType("Online");
        responseDto.setTrainerName("Rahul");

        // Mock behavior
        Mockito.when(mapper.toEntity(requestDto)).thenReturn(course);
        Mockito.when(repository.save(course)).thenReturn(savedCourse);
        Mockito.when(mapper.toResponseDto(savedCourse)).thenReturn(responseDto);

        // Actual service method call
        CourseResponseDto result = service.saveCourse(requestDto);

        // Result checking
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Backend Development", result.getCourseName());
        assertEquals(15000.0, result.getCourseFee());
        assertEquals(4, result.getCourseDuration());
        assertEquals("Online", result.getCourseType());
        assertEquals("Rahul", result.getTrainerName());

        // Method call checking
        Mockito.verify(mapper).toEntity(requestDto);
        Mockito.verify(repository).save(course);
        Mockito.verify(mapper).toResponseDto(savedCourse);
    }
}