package com.schedule.service.impl;

import com.schedule.domain.Student;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.StudentRepo;
import com.schedule.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Override
    public Student findById(Long id) {
        return studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Student with id<%s> not found", id))
        );
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
}
