package com.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.Student;
import com.schedule.service.StudentService;

import lombok.RequiredArgsConstructor;

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

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        return studentService.delete(id);
    }

}
