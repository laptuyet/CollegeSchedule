package com.schedule.service;

import com.schedule.domain.Instructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstructorService {

    Instructor findById(Long id);

    List<Instructor> findAll();
    
    List<Instructor> findAllAvailable();

    List<Instructor> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Instructor save(Instructor objectFromJson);

    Instructor update(Instructor objectFromJson);

    ResponseEntity<String> delete(Long id);
}
