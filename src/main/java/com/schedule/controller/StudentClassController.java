package com.schedule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.StudentClass;
import com.schedule.service.StudentClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/studentClass")
public class StudentClassController {
	
	private final StudentClassService studentClassService;
	
	@GetMapping("/{id}")
	public StudentClass getClass(@PathVariable Long id) {
		return studentClassService.findById(id);
	}
	
	@PostMapping("/add")
	public StudentClass createClass(@RequestBody StudentClass studentClass) {
		return studentClassService.create(studentClass);
	}
	
	@PutMapping("/update")
	public StudentClass updateClass(@RequestBody StudentClass studentClass) {
		return studentClassService.update(studentClass);
	}
}
