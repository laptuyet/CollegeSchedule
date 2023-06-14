package com.schedule.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schedule.domain.StudentTimeTable;

public interface StudentTimeTableRepo extends JpaRepository<StudentTimeTable, Long>{
	Optional<StudentTimeTable> findByStudentIdAndTimeTableId(Long studentId, Integer timeTableId);
	
	List<StudentTimeTable> findByStudentId(Long studentId);
}
