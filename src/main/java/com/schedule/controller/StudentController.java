package com.schedule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.schedule.domain.Instructor;
import com.schedule.domain.Student;
import com.schedule.service.JsonMapperService;
import com.schedule.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/all/paging")
    public List<Student> getAllStudents(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return studentService.findAll(pageNo, pageSize, sortBy);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity
                .ok(studentService.save(student));
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent (@RequestBody Student student) {
        return ResponseEntity
                .ok(studentService.update(student));
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        return studentService.delete(id);
    }

}
