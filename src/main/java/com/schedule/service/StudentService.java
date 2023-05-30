package com.schedule.service;

import com.schedule.domain.Instructor;
import com.schedule.domain.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    Student findById(Long id);

    List<Student> findAll();

    List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Student save(Student objectFromJson);

    Student update(Student objectFromJson);

    ResponseEntity<String> delete(Long id);
}
