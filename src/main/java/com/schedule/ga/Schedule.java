package com.schedule.ga;

import com.schedule.domain.Class;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<Class> classes;
    private DataForSchedule dataForSchedule;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int classNumb = 0;
    private int numbOfConflicts = 0;

    public DataForSchedule getData() {
        return this.dataForSchedule;
    }

    public Schedule(DataForSchedule dataForSchedule) {
        this.dataForSchedule = dataForSchedule;
        classes = new ArrayList<Class>(dataForSchedule.getNumberOfClasses());
    }

    public Schedule initialize() {
        // randomly initialize the schedule
        new ArrayList<>(dataForSchedule.getDepts()).forEach(dept -> {
            dept.getCourses().forEach(course -> {
                Class newClass = new Class(classNumb++, dept, course);
                newClass.setMeetingTime(dataForSchedule.getMeetingTimes().get((int) (dataForSchedule.getMeetingTimes().size() * Math.random())));
                newClass.setRoom(dataForSchedule.getRooms().get((int) (dataForSchedule.getRooms().size() * Math.random())));
                newClass.setInstructor(course.getInstructorCourses().get((int) (course.getInstructorCourses().size() * Math.random())).getInstructor());
                classes.add(newClass);
            });
        });
        return this;
    }

    public int getNumberOfConflicts() {
        return numbOfConflicts;
    }

    public List<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    private double calculateFitness() {
        /**
         * Tính độ phù hợp cho từng lớp học
         * Điều kiện:
         *  1. size của phòng học phải >= số lượng student tối đa của 1 lớp
         *  2. những lớp từ lớp x trở đi ko trùng phòng và ko dc trừng giáo viện dạy
         *
         *  +1 point nếu 1 class nào đó ko meet the conditions
         * */
        numbOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumberOfStudents()) numbOfConflicts++;
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
                    if (x.getRoom() == y.getRoom()) numbOfConflicts++;
                    if (x.getInstructor() == y.getInstructor()) numbOfConflicts++;
                }
            });
        });

        return 1 / (double) (numbOfConflicts + 1);
    }

    public String toString() {
        String returnValue = "";
        for (int x = 0; x < classes.size() - 1; x++)
            returnValue += classes.get(x) + ",";
        returnValue += classes.get(classes.size() - 1);
        return returnValue;
    }

}
