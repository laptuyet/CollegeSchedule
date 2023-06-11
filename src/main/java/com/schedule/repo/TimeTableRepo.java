package com.schedule.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schedule.domain.TimeTable;

public interface TimeTableRepo extends JpaRepository<TimeTable, Integer>{
	
	Optional<TimeTable> findByCourseNumber(String number); 
	
}
