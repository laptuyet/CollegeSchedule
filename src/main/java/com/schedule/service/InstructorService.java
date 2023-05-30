package com.schedule.service;

import com.schedule.domain.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor findById(Long id);

    List<Instructor> findAll();
}
