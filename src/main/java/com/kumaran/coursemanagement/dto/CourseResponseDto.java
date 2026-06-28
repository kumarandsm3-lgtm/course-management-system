package com.kumaran.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long id;

    private String courseName;

    private Double courseFee;

    private int courseDuration;

    private String courseType;

    private String trainerName;

    private boolean active;

}