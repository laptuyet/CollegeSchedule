package com.schedule.service.impl;

import com.schedule.domain.Instructor;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.InstructorRepo;
import com.schedule.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;

    @Override
    public Instructor findById(Long id) {
        return instructorRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Instructor with id<%s> not found", id))
        );
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepo.findAll();
    }
}
