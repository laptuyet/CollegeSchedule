package com.schedule.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.Instructor;
import com.schedule.service.InstructorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/all")
    public List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/{idOrUsername}")
    public Instructor getInstructorById(@PathVariable Object idOrUsername) {
    	
    	try {
    		Long id = Long.valueOf(String.valueOf(idOrUsername));
    		return instructorService.findById(id);
    	} catch (NumberFormatException ex) {
    		String username = String.valueOf(idOrUsername);
    		return instructorService.findByUsername(username);
    	}
    }

    @GetMapping("/all/paging")
    public List<Instructor> getAllInstructors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return instructorService.findAll(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("hasRole('ROLE_OFFICER')")
    @PostMapping("/create")
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity
                .ok(instructorService.save(instructor));
    }

    @PreAuthorize("hasRole('ROLE_OFFICER')")
    @PutMapping("/update")
    public ResponseEntity<Instructor> updateInstructor (@RequestBody Instructor instructor) {
        return ResponseEntity
                .ok(instructorService.update(instructor));
    }

    @PreAuthorize("hasRole('ROLE_OFFICER')")
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteInstructorById(@PathVariable Long id) {
        return instructorService.delete(id);
    }
}
