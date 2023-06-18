package com.schedule.service;

import com.schedule.domain.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long id);
    
    Department findByName(String name);

    List<Department> findAll();
}
