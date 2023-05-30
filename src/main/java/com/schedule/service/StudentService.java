package com.schedule.service;

import com.schedule.domain.Student;

import java.util.List;

public interface StudentService {

    Student findById(Long id);

    List<Student> findAll();
}
