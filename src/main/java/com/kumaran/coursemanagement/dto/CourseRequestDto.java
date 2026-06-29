package com.kumaran.coursemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @Positive(message = "Course fee must be greater than 0")
    private Double courseFee;

    @Positive(message = "Course duration must be greater than 0")
    private int courseDuration;

    @NotBlank(message = "Course type is required")
    private String courseType;
}