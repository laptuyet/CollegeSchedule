package com.schedule.service;

import com.schedule.domain.InstructorCourse;

import java.util.List;

public interface InstructorCourseService {

    InstructorCourse findById(Long id);

    List<InstructorCourse> findAll();

    List<InstructorCourse> findAllByCourse(Long courseId);

    List<InstructorCourse> findAllByInstructor(Long instructorId);

}
