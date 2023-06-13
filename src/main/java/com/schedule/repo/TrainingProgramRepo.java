package com.schedule.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schedule.domain.TrainingProgram;
import com.schedule.model.Major;

public interface TrainingProgramRepo extends JpaRepository<TrainingProgram, Long>{

	List<TrainingProgram> findAllByMajor(Major major);
	
	Optional<TrainingProgram> findByMajorAndCourseNumber(Major major, String courseNumber);
	
	void deleteByMajorAndCourseNumber(Major major, String courseNumber);
}
