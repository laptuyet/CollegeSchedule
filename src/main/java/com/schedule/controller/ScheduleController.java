package com.schedule.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.TimeTable;
import com.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    
    @GetMapping("/getSchedule")
    public List<TimeTable> getSchedule() {
        return scheduleService.getSchedule();
    }
    
    @PreAuthorize("hasRole('ROLE_OFFICER')")
    @GetMapping("/generateSchedule")
    public List<TimeTable> generateSchedule() {
        return scheduleService.generateSchedule();
    }
    
    @PreAuthorize("hasRole('ROLE_OFFICER')")
    @PostMapping("/saveSchedule")
    public List<TimeTable> saveSchedule() {
    	return scheduleService.saveSchedule();
    }
}
