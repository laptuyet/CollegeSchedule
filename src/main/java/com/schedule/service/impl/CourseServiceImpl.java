package com.schedule.service.impl;

import com.schedule.domain.Course;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.CourseRepo;
import com.schedule.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    @Override
    public Course findById(Long number) {
        return courseRepo.findById(number).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Course with id<%s> not found", number)));
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> findAllByDepartment(Long departmentId) {
        return courseRepo.findAllByDepartment(departmentId);
    }
}
