package com.schedule.service;

import com.schedule.domain.Course;
import com.schedule.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    Course findByNumber(String number);

    List<Course> findAll();
    
    List<Course> findAllAvailable();

    List<Course> findAllByDepartment(Long departmentId);

	Course save(CourseDTO courseDto);
	
	Course update(Course course);
	
	String delete(String number);
}
