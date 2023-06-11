package com.schedule.repo;

import com.schedule.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {
	
	Optional<Course> findByNumber(String number);

    List<Course> findAllByDepartmentId(Long departmentId);
    
    List<Course> findAllByIsDeleted(Boolean isDeleted);
}
