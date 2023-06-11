package com.schedule.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/training-program")
public class TrainingProgramController {
	

	@GetMapping("/{departmentId}")
	public void getAllCourseByDepartment(@PathVariable Long departmentId,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect("/api/v1/courses/department/" + departmentId);
	}
	
}
