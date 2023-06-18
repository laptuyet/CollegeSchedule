package com.schedule.repo;

import com.schedule.domain.Department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
	Optional<Department> findByName(String name);
}
