package com.schedule.service;

import java.util.List;

import com.schedule.domain.TimeTable;

public interface TimeTableService {
	TimeTable save(TimeTable timeTable);
	
	List<TimeTable> saveAll(List<TimeTable> timeTables);

	List<TimeTable> findAll();
	
	void deleteAll();
}
