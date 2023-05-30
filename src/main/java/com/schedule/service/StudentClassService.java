package com.schedule.service;

import com.schedule.domain.StudentClass;

import java.util.List;

public interface StudentClassService {

    StudentClass findById(Long id);

    List<StudentClass> findAll();
}
