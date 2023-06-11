package com.schedule.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.schedule.domain.Student;

public interface StudentService {

    Student findById(Long id);
    
    Student findByUsername(String username);

    List<Student> findAll();

    List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Student save(Student objectFromJson);

    Student update(Student objectFromJson);

    ResponseEntity<String> delete(Long id);
}
