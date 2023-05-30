package com.schedule.service.impl;

import com.schedule.domain.InstructorCourse;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.InstructorCourseRepo;
import com.schedule.service.InstructorCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorCourseServiceImpl implements InstructorCourseService {

    private final InstructorCourseRepo instructorCourseRepo;

    @Override
    public InstructorCourse findById(Long id) {
        return instructorCourseRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("InstructorCourse with id<%s> not found", id))
        );
    }

    @Override
    public List<InstructorCourse> findAll() {
        return instructorCourseRepo.findAll();
    }

    @Override
    public List<InstructorCourse> findAllByCourse(Long courseId) {
        return instructorCourseRepo.findAllByCourse(courseId);
    }

    @Override
    public List<InstructorCourse> findAllByInstructor(Long instructorId) {
        return instructorCourseRepo.findAllByInstructor(instructorId);
    }
}
