package com.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.TrainingProgram;
import com.schedule.model.Major;
import com.schedule.service.TrainingProgramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/training-program")
public class TrainingProgramController {
	
	private final TrainingProgramService trainingProgramService;
	
	@GetMapping("/{major}")
	public List<TrainingProgram> getAllCourseByMajor(@PathVariable Major major) {
		return trainingProgramService.findAllByMajor(major);
	}
	
	@PostMapping("/add/{major}/{courseNumber}")
	public TrainingProgram addCourseToTrainingProgram(
			@PathVariable Major major,
			@PathVariable String courseNumber) {
		return trainingProgramService.save(major, courseNumber);
	}
	
	@PutMapping("/update/{major}/{oldCourseNumber}/{newCourseNumber}")
	public TrainingProgram updateCourseOfTrainingProgram(
			@PathVariable Major major,
			@PathVariable String oldCourseNumber,
			@PathVariable String newCourseNumber
			) {
		return trainingProgramService.update(major, oldCourseNumber, newCourseNumber);
	}
	
	@DeleteMapping("/delete/{major}/{courseNumber}")
	public ResponseEntity<String> deleteCourseFromTrainingProgram(
			@PathVariable Major major,
			@PathVariable String courseNumber
			) {
		return ResponseEntity.ok(trainingProgramService.delete(major, courseNumber));
	}
	
}
