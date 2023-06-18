package com.schedule.service.impl;

import com.schedule.domain.Department;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.DepartmentRepo;
import com.schedule.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;

    @Override
    public Department findById(Long id) {
        return departmentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Department with id<%d> not found", id)));
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

	@Override
	public Department findByName(String name) {
		return departmentRepo.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Department with name<%s> not found", name)));
	}
}
