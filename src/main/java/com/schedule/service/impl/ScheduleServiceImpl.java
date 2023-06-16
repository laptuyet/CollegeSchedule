package com.schedule.service.impl;

import com.schedule.domain.Course;
import com.schedule.domain.Instructor;
import com.schedule.domain.TimeTable;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.ga.DataForSchedule;
import com.schedule.ga.Driver;
import com.schedule.ga.Schedule;
import com.schedule.service.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

	private final RoomService roomService;

	private final InstructorService instructorService;

	private final CourseService courseService;

	private final DepartmentService departmentService;

	private final MeetingTimeService meetingTimeService;
	
	private final TimeTableService timeTableService;

	private List<TimeTable> timeTables = new ArrayList<>();
	
	private TimeTable timeTable = null;

	@Override
	public List<TimeTable> generateSchedule() {
		timeTables.clear();
		DataForSchedule dataForSchedule = new DataForSchedule(roomService, instructorService, courseService,
				departmentService, meetingTimeService);

		Driver driver = new Driver(dataForSchedule);
		Schedule schedule = driver.demoScheduling();
		schedule.getClasses().forEach(c -> timeTables.add(
				new TimeTable(
						c.getId()+1,
						c.getDept(),
						c.getCourse(),
						c.getInstructor(),
						c.getMeetingTime(),
						c.getRoom())
			)
		);
		
		return timeTables;
	}

	@Override
	public List<TimeTable> saveSchedule() {
		if (timeTables.isEmpty()) {
			throw new ResourceNotFoundException("There is no timetables for saving, just generate one and save after that!");
		}
		
		// delete old schedule
		timeTableService.deleteAll();
		
		List<TimeTable> savedTimeTables = timeTableService.saveAll(timeTables);
		timeTables.clear();
		return savedTimeTables;
	}

	@Override
	public List<TimeTable> getSchedule() {
		return timeTableService.findAll();
	}

	@Override
	public TimeTable generateExtra(Long instructorId, String courseNumber) {
		
		var schedule = getSchedule();
		
		Instructor instructor = instructorService.findById(instructorId);
		
		Course course = courseService.findByNumber(courseNumber);
		
		TimeTable newtimeTable = Driver.generateExtra(instructor, course, schedule, roomService, meetingTimeService);
		
		timeTable = newtimeTable;
		
		return timeTable;
	}

	@Override
	public TimeTable saveExtra() {
		if (timeTable == null) {
			throw new ResourceNotFoundException("There is no timetables for saving, just generate one and save after that!");
		}
		
		TimeTable saveTimeTable = timeTableService.save(timeTable);
		
		timeTable = null;
		
		return saveTimeTable;
	}
}
