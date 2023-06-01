package com.schedule.service;

import java.util.List;

import com.schedule.domain.TimeTable;

public interface ScheduleService {
	List<TimeTable> generateSchedule();
    
    List<TimeTable> saveSchedule();

	List<TimeTable> getSchedule();
}
