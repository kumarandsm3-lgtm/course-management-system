package com.kumaran.coursemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CourseRequestDto {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @Positive(message = "Course fee must be greater than 0")
    private double courseFee;

    @Positive(message = "Course duration must be greater than 0")
    private int courseDuration;

    @NotBlank(message = "Course type is required")
    private String courseType;

    public CourseRequestDto() {
    }

    public CourseRequestDto(String courseName, double courseFee, int courseDuration, String courseType) {
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseDuration = courseDuration;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}