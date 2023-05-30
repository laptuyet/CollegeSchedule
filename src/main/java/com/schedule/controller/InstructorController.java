package com.schedule.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.schedule.domain.Instructor;
import com.schedule.service.InstructorService;
import com.schedule.service.JsonMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/all")
    public List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable Long id) {
        return instructorService.findById(id);
    }

    @GetMapping("/all/paging")
    public List<Instructor> getAllInstructors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return instructorService.findAll(pageNo, pageSize, sortBy);
    }

    @PostMapping("/create")
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity
                .ok(instructorService.save(instructor));
    }

    @PutMapping("/update")
    public ResponseEntity<Instructor> updateInstructor (@RequestBody Instructor instructor) {
        return ResponseEntity
                .ok(instructorService.update(instructor));
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteInstructorById(@PathVariable Long id) {
        return instructorService.delete(id);
    }
}
