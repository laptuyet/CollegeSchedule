package com.schedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schedule.domain.TimeTable;

public interface TimeTableRepo extends JpaRepository<TimeTable, Integer>{

}
