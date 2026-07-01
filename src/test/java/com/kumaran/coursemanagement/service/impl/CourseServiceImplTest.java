package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.entity.Course;
import com.kumaran.coursemanagement.exception.ResourceNotFoundException;
import com.kumaran.coursemanagement.mapper.CourseMapper;
import com.kumaran.coursemanagement.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void getAllCourses_shouldReturnCourseList() {

        Course course1 = new Course();
        course1.setId(1L);
        course1.setCourseName("Java Backend Development");
        course1.setCourseFee(15000.0);
        course1.setCourseDuration(4);
        course1.setCourseType("Online");
        course1.setTrainerName("Rahul");
        course1.setActive(true);

        Course course2 = new Course();
        course2.setId(2L);
        course2.setCourseName("React Frontend Development");
        course2.setCourseFee(12000.0);
        course2.setCourseDuration(3);
        course2.setCourseType("Online");
        course2.setTrainerName("Arun");
        course2.setActive(true);

        CourseResponseDto responseDto1 = new CourseResponseDto();
        responseDto1.setId(1L);
        responseDto1.setCourseName("Java Backend Development");
        responseDto1.setCourseFee(15000.0);
        responseDto1.setCourseDuration(4);
        responseDto1.setCourseType("Online");
        responseDto1.setTrainerName("Rahul");

        CourseResponseDto responseDto2 = new CourseResponseDto();
        responseDto2.setId(2L);
        responseDto2.setCourseName("React Frontend Development");
        responseDto2.setCourseFee(12000.0);
        responseDto2.setCourseDuration(3);
        responseDto2.setCourseType("Online");
        responseDto2.setTrainerName("Arun");

        Mockito.when(repository.findByActiveTrue())
                .thenReturn(List.of(course1, course2));

        Mockito.when(mapper.toResponseDto(course1))
                .thenReturn(responseDto1);

        Mockito.when(mapper.toResponseDto(course2))
                .thenReturn(responseDto2);

        List<CourseResponseDto> result = service.getAllCourses();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("Java Backend Development", result.get(0).getCourseName());
        assertEquals(15000.0, result.get(0).getCourseFee());
        assertEquals(4, result.get(0).getCourseDuration());
        assertEquals("Online", result.get(0).getCourseType());
        assertEquals("Rahul", result.get(0).getTrainerName());

        assertEquals(2L, result.get(1).getId());
        assertEquals("React Frontend Development", result.get(1).getCourseName());
        assertEquals(12000.0, result.get(1).getCourseFee());
        assertEquals(3, result.get(1).getCourseDuration());
        assertEquals("Online", result.get(1).getCourseType());
        assertEquals("Arun", result.get(1).getTrainerName());

        Mockito.verify(repository).findByActiveTrue();
        Mockito.verify(mapper).toResponseDto(course1);
        Mockito.verify(mapper).toResponseDto(course2);
    }

    @Test
    void getCourseById_whenCourseNotFound_shouldThrowException() {

        Long courseId = 99L;

        Mockito.when(repository.findByIdAndActiveTrue(courseId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getCourseById(courseId)
        );

        assertNotNull(exception);

        Mockito.verify(repository).findByIdAndActiveTrue(courseId);
        Mockito.verifyNoInteractions(mapper);
    }

    @Test
    void deleteCourse_shouldSoftDeleteCourse() {

        Long courseId = 1L;

        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Java Backend Development");
        course.setCourseFee(15000.0);
        course.setCourseDuration(4);
        course.setCourseType("Online");
        course.setTrainerName("Rahul");
        course.setActive(true);

        Mockito.when(repository.findByIdAndActiveTrue(courseId))
                .thenReturn(Optional.of(course));

        service.deleteCourse(courseId);

        assertFalse(course.isActive());

        Mockito.verify(repository).findByIdAndActiveTrue(courseId);
        Mockito.verify(repository).save(course);
        Mockito.verifyNoInteractions(mapper);
    }
}