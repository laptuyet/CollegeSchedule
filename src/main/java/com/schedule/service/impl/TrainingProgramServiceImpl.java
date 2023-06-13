package com.schedule.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schedule.domain.TrainingProgram;
import com.schedule.exception.ExistingResourceException;
import com.schedule.model.Major;
import com.schedule.repo.TrainingProgramRepo;
import com.schedule.service.CourseService;
import com.schedule.service.TrainingProgramService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService{
	
	private final TrainingProgramRepo trainingProgramRepo;
	
	private final CourseService courseService;

	@Override
	public List<TrainingProgram> findAllByMajor(Major major) {
		return trainingProgramRepo.findAllByMajor(major);
	}

	@Override
	public TrainingProgram save(Major major, String courseNumber) {
		
		if (trainingProgramRepo.findByMajorAndCourseNumber(major, courseNumber).isPresent()) {
			throw new ExistingResourceException("Course <%s> is existing in <%s> training program"
					.formatted(courseNumber, major.getDetailName()));
		}
		
		TrainingProgram trainingProgram = TrainingProgram
				.builder()
				.major(major)
				.course(courseService.findByNumber(courseNumber))
				.build();
		
		return trainingProgramRepo.save(trainingProgram);
	}

	@Override
	public TrainingProgram update(Major major, String oldCourseNumber, String newCourseNumber) {
		Optional<TrainingProgram> optTrainingProgram =  trainingProgramRepo.findByMajorAndCourseNumber(major, oldCourseNumber);
		if (!optTrainingProgram.isPresent()) {
			throw new ExistingResourceException("Course <%s> is not existing in <%s> training program"
					.formatted(oldCourseNumber, major.getDetailName()));
		}
		
		TrainingProgram foundTrainingProgram = optTrainingProgram.get();
		
		foundTrainingProgram.setCourse(courseService.findByNumber(newCourseNumber));
		
		return trainingProgramRepo.save(foundTrainingProgram);
	}

	@Override
	public String delete(Major major, String courseNumber) {
		
		Optional<TrainingProgram> optTrainingProgram =  trainingProgramRepo.findByMajorAndCourseNumber(major, courseNumber);
		if (!optTrainingProgram.isPresent()) {
			throw new ExistingResourceException("Course <%s> is not existing in <%s> training program"
					.formatted(courseNumber, major.getDetailName()));
		}
		
		TrainingProgram foundTrainingProgram = optTrainingProgram.get();
		trainingProgramRepo.deleteById(foundTrainingProgram.getId());
		
		return "Delete course <%s> in <%s> training program successfully!".formatted(courseNumber, major.getDetailName());
	}

}
