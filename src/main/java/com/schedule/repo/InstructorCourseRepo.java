package com.schedule.repo;

import com.schedule.domain.InstructorCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorCourseRepo extends JpaRepository<InstructorCourse, Long> {

    List<InstructorCourse> findAllByCourse(Long courseId);

    List<InstructorCourse> findAllByInstructor(Long instructorId);
}
