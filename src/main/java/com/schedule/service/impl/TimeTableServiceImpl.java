package com.schedule.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schedule.domain.TimeTable;
import com.schedule.repo.TimeTableRepo;
import com.schedule.service.TimeTableService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {
	
	private final TimeTableRepo timeTableRepo;

	@Override
	public TimeTable save(TimeTable timeTable) {
		return timeTableRepo.save(timeTable);
	}

	@Override
	public List<TimeTable> saveAll(List<TimeTable> timeTables) {
		return timeTableRepo.saveAll(timeTables);
	}

	@Override
	public List<TimeTable> findAll() {
		return timeTableRepo.findAll();
	}

	@Override
	public void deleteAll() {
		timeTableRepo.deleteAll();
	}
	
	
}
