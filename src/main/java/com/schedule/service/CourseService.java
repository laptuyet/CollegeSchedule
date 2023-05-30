package com.schedule.service;

import com.schedule.domain.Course;

import java.util.List;

public interface CourseService {

    Course findById(Long number);

    List<Course> findAll();

    List<Course> findAllByDepartment(Long departmentId);
}
