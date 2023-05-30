package com.schedule.repo;

import com.schedule.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findAllByDepartment(Long departmentId);
}
