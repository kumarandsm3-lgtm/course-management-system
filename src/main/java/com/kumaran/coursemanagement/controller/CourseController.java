package com.kumaran.coursemanagement.controller;

import com.kumaran.coursemanagement.dto.CourseRequestDto;
import com.kumaran.coursemanagement.dto.CourseResponseDto;
import com.kumaran.coursemanagement.response.ApiResponse;
import com.kumaran.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDto>> saveCourse(
            @Valid @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto response = service.saveCourse(requestDto);

        ApiResponse<CourseResponseDto> apiResponse =
                new ApiResponse<>(true, "Course created successfully", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getAllCourses() {

        List<CourseResponseDto> response = service.getAllCourses();

        ApiResponse<List<CourseResponseDto>> apiResponse =
                new ApiResponse<>(true, "Courses fetched successfully", response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getCoursesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        List<CourseResponseDto> response =
                service.getCoursesWithPagination(page, size, sortBy, direction);

        ApiResponse<List<CourseResponseDto>> apiResponse =
                new ApiResponse<>(
                        true,
                        "Courses fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> searchCourses(
            @RequestParam String keyword) {

        List<CourseResponseDto> response = service.searchCourses(keyword);

        ApiResponse<List<CourseResponseDto>> apiResponse =
                new ApiResponse<>(
                        true,
                        "Courses searched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> getCourseById(
            @PathVariable Long id) {

        CourseResponseDto response = service.getCourseById(id);

        ApiResponse<CourseResponseDto> apiResponse =
                new ApiResponse<>(true, "Course fetched successfully", response);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto response = service.updateCourse(id, requestDto);

        ApiResponse<CourseResponseDto> apiResponse =
                new ApiResponse<>(true, "Course updated successfully", response);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(
            @PathVariable Long id) {

        service.deleteCourse(id);

        ApiResponse<String> apiResponse =
                new ApiResponse<>(true, "Course deleted successfully", null);

        return ResponseEntity.ok(apiResponse);
    }
}