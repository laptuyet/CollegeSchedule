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
//        // FAKE DATA FOR DEMO
//
//        // ROOMS
//        Room r1 = new Room("R1", 110);
//        Room r2 = new Room("R2", 100);
//        Room r3 = new Room("R3", 120);
//        this.rooms = Arrays.asList(r1, r2, r3);
        this.rooms = roomService.findAll();
//
//        // INSTRUCTORS
//        Instructor i1 = new Instructor("I1", "Mr. Duy");
//        Instructor i2 = new Instructor("I2", "Mr. Kiên");
//        Instructor i3 = new Instructor("I3", "Mrs. Thảo");
//        Instructor i4 = new Instructor("I4", "Mr. Thái");
//        this.instructors = Arrays.asList(i1, i2, i3, i4);
        this.instructors = instructorService.findAll();

//        // MEETING TIMES
//        MeetingTime mt1 = new MeetingTime("MT1", "MWF 09:00 - 10:00");
//        MeetingTime mt2 = new MeetingTime("MT2", "MWF 10:00 - 11:00");
//        MeetingTime mt3 = new MeetingTime("MT3", "TTH 09:00 - 10:30");
//        MeetingTime mt4 = new MeetingTime("MT4", "TTH 10:30 - 12:00");
//        this.meetingTimes = Arrays.asList(mt1, mt2, mt3, mt4);
        this.meetingTimes = meetingTimeService.findALl();

//        // COURSES
//        Course c1 = new Course("C1", "INT1", Arrays.asList(i1, i2), 95);
//        Course c2 = new Course("C2", "INT2", Arrays.asList(i1, i2, i3), 90);
//        Course c3 = new Course("C3", "INT3", Arrays.asList(i1, i2), 120);
//        Course c4 = new Course("C4", "INT4", Arrays.asList(i3, i4), 100);
//        Course c5 = new Course("C5", "INT5", Arrays.asList(i4), 80);
//        Course c6 = new Course("C6", "INT6", Arrays.asList(i1, i3), 85);
//        Course c7 = new Course("C7", "INT7", Arrays.asList(i2, i4), 100);
//        this.courses = Arrays.asList(c1, c2, c3, c4, c5, c6, c7);
        this.courses = courseService.findAll();

//        // DEPARTMENTS
//        Department d1 = new Department("MATH", Arrays.asList(c1, c3));
//        Department d2 = new Department("EE", Arrays.asList(c2, c4, c5));
//        Department d3 = new Department("PHY", Arrays.asList(c6, c7));
//        this.depts = Arrays.asList(d1, d2, d3);
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
