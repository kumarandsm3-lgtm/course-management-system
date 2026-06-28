package com.kumaran.coursemanagement.controller;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<CourseResponseDto> saveCourse(@RequestBody CourseRequestDto requestDto) {

        CourseResponseDto response = service.saveCourse(requestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {

        List<CourseResponseDto> response = service.getAllCourses();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {

        CourseResponseDto response = service.getCourseById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto response = service.updateCourse(id, requestDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

        service.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully");
    }
}