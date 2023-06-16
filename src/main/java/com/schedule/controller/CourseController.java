package com.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.Course;
import com.schedule.dto.CourseDTO;
import com.schedule.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/courses/")
@RequiredArgsConstructor
public class CourseController {
	
	private final CourseService courseService;
	
	@GetMapping("/all")
	public List<Course> getAllCourses() {
		return courseService.findAll();
	}
	
	@GetMapping("/{number}")
	public Course getCourse(@PathVariable String number) {
		return courseService.findByNumber(number);
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Course> getAllCoursesByDepartment(@PathVariable Long departmentId) {
		return courseService.findAllByDepartment(departmentId);
	}
	
	@PostMapping("/create")
	public Course createCourse(@RequestBody CourseDTO courseDto) {
		return courseService.save(courseDto);
	}
	
	@PutMapping("/update")
	public Course updateCourse(@RequestBody Course course) {
		return courseService.update(course);
	}

	@DeleteMapping("/delete/{number}")
	public ResponseEntity<String> deleteCourse(@PathVariable String number) {
		return ResponseEntity.ok(courseService.delete(number));
	}
}
