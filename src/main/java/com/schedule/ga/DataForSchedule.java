package com.schedule.ga;

import com.schedule.domain.*;
import com.schedule.service.*;
import lombok.Data;

import java.util.List;

@Data
public class DataForSchedule {
    private List<Room> rooms;
    private List<Instructor> instructors;
    private List<Course> courses;
    private List<Department> depts;
    private List<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;

    public DataForSchedule(
            RoomService roomService,
            InstructorService instructorService,
            CourseService courseService,
            DepartmentService departmentService,
            MeetingTimeService meetingTimeService
    ) {
        initialize(
                roomService,
                instructorService,
                courseService,
                departmentService,
                meetingTimeService
        );
    }

    private DataForSchedule initialize(
            RoomService roomService,
            InstructorService instructorService,
            CourseService courseService,
            DepartmentService departmentService,
            MeetingTimeService meetingTimeService
    ) {

        this.rooms = roomService.findAllAvailable();
        
        this.instructors = instructorService.findAllAvailable();
        
        this.meetingTimes = meetingTimeService.findAll();
        
        this.courses = courseService.findAll();
        
        this.depts = departmentService.findAll();

        // TOTAL CLASSES
        this.depts.forEach(d -> {
            numberOfClasses += d.getCourses().size();
        });

        return this;
    }

//    public List<Room> getRooms() {
//        return rooms;
//    }
//
//    public List<Instructor> getInstructors() {
//        return instructors;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public List<Department> getDepts() {
//        return depts;
//    }
//
//    public List<MeetingTime> getMeetingTimes() {
//        return meetingTimes;
//    }
//
//    public int getNumberOfClasses() {
//        return this.numberOfClasses;
//    }

}
