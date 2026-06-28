package com.kumaran.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    private String courseName;

    private Double courseFee;

    private int courseDuration;

    private String courseType;

}