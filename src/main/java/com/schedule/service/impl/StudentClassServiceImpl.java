package com.schedule.service.impl;

import com.schedule.domain.StudentClass;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.StudentClassRepo;
import com.schedule.service.StudentClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassRepo studentClassRepo;

    @Override
    public StudentClass findById(Long id) {
        return studentClassRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("StudentClass with id<%s> not found", id))
        );
    }

    @Override
    public List<StudentClass> findAll() {
        return studentClassRepo.findAll();
    }
}
