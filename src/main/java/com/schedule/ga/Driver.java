package com.schedule.ga;

import java.util.List;

import com.schedule.domain.Class;
import com.schedule.domain.Course;
import com.schedule.domain.Instructor;
import com.schedule.domain.TimeTable;
import com.schedule.service.MeetingTimeService;
import com.schedule.service.RoomService;

public class Driver {
    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SCHEDULES = 1;
    private int scheduleNumb = 0;
    private int classNumb = 1;
    private DataForSchedule dataForSchedule;

    public Driver(DataForSchedule dataForSchedule) {
        this.dataForSchedule = dataForSchedule;
    }

    public Schedule demoScheduling() {

        int generationNumber = 0;

//        driver.printAvailableData();

        System.out.println("> Generation # " + generationNumber);
        System.out.print("  Schedule # |                                        ");
        System.out.print("Classes [dept, class, room, instructor, meeting.time]");
        System.out.println("                      |Fitness | Conflicts");
        System.out.print("........................................................................");
        System.out.println("............................................................................");

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(dataForSchedule);
        Population population = new Population(Driver.POPULATION_SIZE, dataForSchedule).sortByFitness();
        population.getSchedules().
                forEach(schedule -> System.out.println("     " + scheduleNumb++
                        + "    | " + schedule + " | " + String.format("%.5f", schedule.getFitness())
                        + " | " + schedule.getNumberOfConflicts()));

        printScheduleAsTable(population.getSchedules().get(0), generationNumber);

        classNumb = 1;
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            ++generationNumber;
            System.out.println("> Generation # " + ++generationNumber);
            System.out.print("     Schedule # |                                   ");
            System.out.print("Classes [dept,class,room,instructor,meeting.time]    ");
            System.out.println("                              | Fitness | Conflicts");
            System.out.print("......................................................................");
            System.out.print("...........................................................................");
            System.out.println();
            population = geneticAlgorithm.evolve(population).sortByFitness();
            scheduleNumb = 0;
            population.getSchedules().forEach(schedule -> System.out.println("    " + scheduleNumb++ + "    | " + schedule + "  |  " + String.format("%.5f", schedule.getFitness()) + "  |  " + schedule.getNumberOfConflicts()));
            printScheduleAsTable(population.getSchedules().get(0), generationNumber);
            classNumb = 1;
            System.out.println();
        }
        return population.getSchedules().get(0);
    }

    public void printScheduleAsTable(Schedule schedule, int generation) {
        List<Class> classes = schedule.getClasses();
        System.out.print("\n                      ");
        System.out.println("\nClass # | Dept | Course (number, max # number of students) | Room (Capacity) | Instructor (Id) | Meeting Time");
        //System.out.print("              ");
        System.out.print("...................................................................................");
        System.out.print(".......................................................................................");
        classes.forEach(x -> {
            int majorIndex = dataForSchedule.getDepts().indexOf(x.getDept());
            int coursesIndex = dataForSchedule.getCourses().indexOf(x.getCourse());
            int roomsIndex = dataForSchedule.getRooms().indexOf(x.getRoom());
            int instructorsIndex = dataForSchedule.getInstructors().indexOf(x.getInstructor());
            int meetingTimeIndex = dataForSchedule.getMeetingTimes().indexOf(x.getMeetingTime());
            //System.out.print("                     ");
            System.out.print(String.format("\n  %1$02d  ", classNumb) + "  |  ");
            System.out.print(String.format("%1$4s", dataForSchedule.getDepts().get(majorIndex).getName()) + "  |  ");
            System.out.print(String.format("%1$21s", dataForSchedule.getCourses().get(coursesIndex).getName() + " (" + dataForSchedule.getCourses().get(coursesIndex).getNumber() + ", " + x.getCourse().getMaxNumberOfStudents()) + ")    | ");
            System.out.print(String.format("%1$10s", dataForSchedule.getRooms().get(roomsIndex).getName() + "(" + x.getRoom().getSeatingCapacity()) + ")      |  ");
            System.out.print(String.format("%1$15s", dataForSchedule.getInstructors().get(instructorsIndex).getLname() + " (" + dataForSchedule.getInstructors().get(instructorsIndex).getId() + ")") + "  | ");
            System.out.print(dataForSchedule.getMeetingTimes().get(meetingTimeIndex).getTime() + " (" + dataForSchedule.getMeetingTimes().get(meetingTimeIndex).getId() + ")");
            classNumb++;
        });
        System.out.println();
        if (schedule.getFitness() == 1) System.out.print("\n> Solution Found in " + (generation + 1) + "generations\n");
        System.out.print("...................................................................................");
        System.out.print(".......................................................................................");

    }

    private void printAvailableData() {
        System.out.println("\n------ Available Lecturers ------");
        dataForSchedule.getInstructors().forEach(x -> System.out.println("id: " + x.getId() + ", name: " + x.getLname()));

        System.out.println("\n------ Available Course ------");
        dataForSchedule.getCourses().forEach(x -> System.out.println("Course Title: " + x.getName() + "\nMax # of Students: " + x.getMaxNumberOfStudents() + " \nInstructor(s): " + x.getInstructorCourses() + "\n"));

        System.out.println("\n------ Available Lecture Venues ------");
        dataForSchedule.getRooms().forEach(x -> System.out.println("Venue Title: " + x.getNumber() + "\nMax Seating Capacity: " + x.getSeatingCapacity() + "\n"));

        System.out.println("\n------ Available Meeting Times ------");
        dataForSchedule.getMeetingTimes().forEach(x -> System.out.println("id: " + x.getId() + ", meeting time:" + x.getTime()));

        System.out.println("\n\n------ Available Classes ------");
        dataForSchedule.getDepts().forEach(x -> System.out.println("Class Name:  " + x.getName() + "\nCourses Offered:  " + x.getCourses() + "\n"));
        System.out.print("...........................................................................................");
        System.out.println("...........................................................................................");
    }

	public static TimeTable generateExtra(
			Instructor instructor,
			Course course,
			List<TimeTable> schedule,
			RoomService roomService,
			MeetingTimeService meetingTimeService) {
		
		var rooms = roomService.findAllAvailable();
		var meetingTimes = meetingTimeService.findAll();
		
		int newId = schedule.get(schedule.size() - 1).getId() + 1;
		
		TimeTable randomTimeTable = TimeTable
				.builder()
				.id(newId)
				.department(course.getDepartment())
				.course(course)
				.instructor(instructor)
				.meetingTime(meetingTimes.get((int)(Math.random() * meetingTimes.size())))
				.room(rooms.get((int)(Math.random() * rooms.size())))
				.build();
		
		randomTimeTable =  Schedule.getExtra(randomTimeTable, schedule, rooms, meetingTimes);
		
		return randomTimeTable;
	}
}
