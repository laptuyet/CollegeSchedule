package com.schedule.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.schedule.domain.Student;
import com.schedule.domain.StudentTimeTable;

public interface StudentService {

    Student findById(Long id);
    
    Student findByUsername(String username);

    List<Student> findAll();

    List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Student save(Student objectFromJson);

    Student update(Student objectFromJson);

    ResponseEntity<String> delete(Long id);

	StudentTimeTable addTimeTable(Long studentId, Integer timeTableId);

	List<StudentTimeTable> getScheduleOfStudent(Long studentId);
}
