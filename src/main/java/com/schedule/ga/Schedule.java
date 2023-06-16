package com.schedule.ga;

import com.schedule.domain.Class;
import com.schedule.domain.Instructor;
import com.schedule.domain.MeetingTime;
import com.schedule.domain.Room;
import com.schedule.domain.TimeTable;

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
            	Instructor instr = course.getInstructorCourses().get((int) (course.getInstructorCourses().size() * Math.random())).getInstructor();
            	if(!instr.getIsQuitJob()) {
            		Class newClass = new Class(classNumb++, dept, course);
                    newClass.setMeetingTime(dataForSchedule.getMeetingTimes().get((int) (dataForSchedule.getMeetingTimes().size() * Math.random())));
                    newClass.setRoom(dataForSchedule.getRooms().get((int) (dataForSchedule.getRooms().size() * Math.random())));
                    newClass.setInstructor(instr);
                    classes.add(newClass);
            	}
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

	public static TimeTable getExtra(
			TimeTable randomTimeTable,
			List<TimeTable> schedule,
			 List<Room> rooms,
			 List<MeetingTime> meetingTimes) {
		
		int numOfGenerate = 1;
		
		while(calculateFitnessForExtra(randomTimeTable, schedule) != 1.0) {
			numOfGenerate++;
			randomTimeTable.setMeetingTime(meetingTimes.get((int)(Math.random() * meetingTimes.size())));
			randomTimeTable.setRoom(rooms.get((int)(Math.random() * rooms.size())));
		}
		
		System.out.println("Generate extra class after: %d times".formatted(numOfGenerate));
		
		return randomTimeTable;
	}
	
	public static double calculateFitnessForExtra(TimeTable randomTimeTable, List<TimeTable> schedule) {
		int numOfConflicts = 0;
		
		if (randomTimeTable.getCourse().getMaxNumberOfStudents() > randomTimeTable.getRoom().getSeatingCapacity())
			numOfConflicts++;
		
		for(TimeTable s : schedule) {
			
			// cũng giảng viên đó, môn đó, nếu cùng phòng mà cùng luôn tgian dạy thì conflict
			if (s.getRoom() == randomTimeTable.getRoom()) {
				if (s.getMeetingTime() == randomTimeTable.getMeetingTime()) {
					numOfConflicts++;
				}
			} else { 
			// cũng giảng viên đó, môn đó, khác phòng nhưng cùng tgian dạy thì conflict (kiểu như phân thân 2 nơi => vô lý)
				if (s.getMeetingTime() == randomTimeTable.getMeetingTime()) {
					numOfConflicts++;
				}
			}
		};
		
		return 1 / (numOfConflicts + 1);
	}

}
