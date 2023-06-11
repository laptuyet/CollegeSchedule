package com.schedule.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schedule.domain.Course;
import com.schedule.domain.TimeTable;
import com.schedule.exception.ExistingResourceException;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.CourseRepo;
import com.schedule.repo.TimeTableRepo;
import com.schedule.service.CourseService;
import com.schedule.validator.ObjectsValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    
    private final TimeTableRepo timeTableRepo;
    
    private final ObjectsValidator<Course> validator;

    @Override
    public Course findByNumber(String number) {
        return courseRepo.findByNumber(number).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Course with number<%s> not found", number)));
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> findAllByDepartment(Long departmentId) {
        return courseRepo.findAllByDepartmentId(departmentId);
    }

	@Override
	public Course save(Course course) {
		validator.validate(course);
		return courseRepo.save(course);
	}

	@Override
	public Course update(Course course) {
		validator.validate(course);
		
		Course foundCourse = findByNumber(course.getNumber());
		
		if(course.getDepartment() != null) {
			foundCourse.setDepartment(course.getDepartment());
		}
		if(course.getInstructorCourses() != null) {
			foundCourse.setInstructorCourses(course.getInstructorCourses());
		}
		if(course.getName() != null) {
			foundCourse.setName(course.getName());
		}
		if(course.getIsDeleted() != null) {
			foundCourse.setIsDeleted(course.getIsDeleted());
		}
		
		foundCourse.setMaxNumberOfStudents(course.getMaxNumberOfStudents());
		
		return courseRepo.save(foundCourse);
	}

	@Override
	public String delete(String number) {
		Course course = findByNumber(number);
		
		// Nếu course đã dc dùng để sắp tkb thì ko thể xóa
		Optional<TimeTable> timeTable = timeTableRepo.findByCourseNumber(number);
		
		if(timeTable.isPresent()) {
			throw new ExistingResourceException(
					String.format("Course %s is used for creating schedule, can't be deleted",
							timeTable.get().getCourse().getName())
					);
		}
		
		course.setIsDeleted(true);
		
		return "Delete course <" + course.getName() + "> successfully!";
	}

	@Override
	public List<Course> findAllAvailable() {
		return courseRepo.findAllByIsDeleted(false);
	}
}
