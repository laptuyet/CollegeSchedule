package com.schedule.service;

import java.util.List;

import com.schedule.domain.TrainingProgram;
import com.schedule.model.Major;

public interface TrainingProgramService {

	List<TrainingProgram> findAllByMajor(Major major);

	TrainingProgram save(Major major, String courseNumber);

	TrainingProgram update(Major major, String oldCourseNumber, String newCourseNumber);

	String delete(Major major, String courseNumber);
}
