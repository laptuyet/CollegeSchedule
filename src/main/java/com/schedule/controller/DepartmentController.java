package com.schedule.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.Department;
import com.schedule.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {
	
	private final DepartmentService departmentService;
	
	@GetMapping("/all")
	public List<Department> getAll() {
		return departmentService.findAll();
	}
	
	@GetMapping("/{identifier}")
	public Department getDepartment(@PathVariable Object identifier) {
		try {
    		Long id = Long.valueOf(String.valueOf(identifier));
    		return departmentService.findById(id);
    	} catch (NumberFormatException ex) {
    		String name = String.valueOf(identifier);
    		return departmentService.findByName(name);
    	}
	}
	
}
