package com.schedule.controller;

import com.schedule.ga.DataForSchedule;
import com.schedule.ga.Driver;
import com.schedule.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/schedules")
public class ScheduleController {

    private final RoomService roomService;

    private final InstructorService instructorService;

    private final CourseService courseService;

    private final DepartmentService departmentService;

    private final MeetingTimeService meetingTimeService;


    @GetMapping("/generateSchedule")
    public void getSchedule() {
        DataForSchedule dataForSchedule = new DataForSchedule(
                roomService,
                instructorService,
                courseService,
                departmentService,
                meetingTimeService);

        Driver driver = new Driver(dataForSchedule);
        driver.demoScheduling();
    }
}
