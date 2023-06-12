package com.schedule.service;

import com.schedule.domain.StudentClass;

import java.util.List;

public interface StudentClassService {

    StudentClass findById(Long id);
    
    StudentClass findByClassName(String className);

    List<StudentClass> findAll();

	StudentClass update(StudentClass studentClass);

	StudentClass create(StudentClass studentClass);
}
