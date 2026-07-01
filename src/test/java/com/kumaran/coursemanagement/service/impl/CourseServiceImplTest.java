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

import java.util.Optional;

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

        CourseRequestDto requestDto = new CourseRequestDto();
        requestDto.setCourseName("Java Backend Development");
        requestDto.setCourseFee(15000.0);
        requestDto.setCourseDuration(4);
        requestDto.setCourseType("Online");

        Course course = new Course();
        course.setCourseName("Java Backend Development");
        course.setCourseFee(15000.0);
        course.setCourseDuration(4);
        course.setCourseType("Online");

        Course savedCourse = new Course();
        savedCourse.setId(1L);
        savedCourse.setCourseName("Java Backend Development");
        savedCourse.setCourseFee(15000.0);
        savedCourse.setCourseDuration(4);
        savedCourse.setCourseType("Online");
        savedCourse.setTrainerName("Rahul");
        savedCourse.setActive(true);

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(1L);
        responseDto.setCourseName("Java Backend Development");
        responseDto.setCourseFee(15000.0);
        responseDto.setCourseDuration(4);
        responseDto.setCourseType("Online");
        responseDto.setTrainerName("Rahul");

        Mockito.when(mapper.toEntity(requestDto)).thenReturn(course);
        Mockito.when(repository.save(course)).thenReturn(savedCourse);
        Mockito.when(mapper.toResponseDto(savedCourse)).thenReturn(responseDto);

        CourseResponseDto result = service.saveCourse(requestDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Backend Development", result.getCourseName());
        assertEquals(15000.0, result.getCourseFee());
        assertEquals(4, result.getCourseDuration());
        assertEquals("Online", result.getCourseType());
        assertEquals("Rahul", result.getTrainerName());

        Mockito.verify(mapper).toEntity(requestDto);
        Mockito.verify(repository).save(course);
        Mockito.verify(mapper).toResponseDto(savedCourse);
    }

    @Test
    void getCourseById_shouldReturnCourseResponse() {

        Long courseId = 1L;

        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Java Backend Development");
        course.setCourseFee(15000.0);
        course.setCourseDuration(4);
        course.setCourseType("Online");
        course.setTrainerName("Rahul");
        course.setActive(true);

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(courseId);
        responseDto.setCourseName("Java Backend Development");
        responseDto.setCourseFee(15000.0);
        responseDto.setCourseDuration(4);
        responseDto.setCourseType("Online");
        responseDto.setTrainerName("Rahul");

        Mockito.when(repository.findByIdAndActiveTrue(courseId))
                .thenReturn(Optional.of(course));

        Mockito.when(mapper.toResponseDto(course))
                .thenReturn(responseDto);

        CourseResponseDto result = service.getCourseById(courseId);

        assertNotNull(result);
        assertEquals(courseId, result.getId());
        assertEquals("Java Backend Development", result.getCourseName());
        assertEquals(15000.0, result.getCourseFee());
        assertEquals(4, result.getCourseDuration());
        assertEquals("Online", result.getCourseType());
        assertEquals("Rahul", result.getTrainerName());

        Mockito.verify(repository).findByIdAndActiveTrue(courseId);
        Mockito.verify(mapper).toResponseDto(course);
    }
}