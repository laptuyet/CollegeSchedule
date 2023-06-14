package com.schedule.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.schedule.domain.Student;
import com.schedule.domain.StudentTimeTable;
import com.schedule.domain.TimeTable;
import com.schedule.exception.ExistingResourceException;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.StudentRepo;
import com.schedule.repo.StudentTimeTableRepo;
import com.schedule.repo.TimeTableRepo;
import com.schedule.service.StudentService;
import com.schedule.service.TimeTableService;
import com.schedule.validator.ObjectsValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final TimeTableRepo timeTableRepo;

	private final StudentTimeTableRepo studentTimeTableRepo;

	private final StudentRepo studentRepo;
	private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);
	private final ObjectsValidator<Student> validator;

	@Override
	public Student findById(Long id) {
		return studentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id<%s> not found", id)));
	}

	@Override
	public List<Student> findAll() {
		return studentRepo.findAll();
	}

	@Override
	public List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Slice<Student> slicePage = studentRepo.findAll(paging);
		return slicePage.getContent();
	}

	@Override
	public Student save(Student student) {
		logger.info("Validate object: " + student);

		validator.validate(student);

		if (studentRepo.findByUsername(student.getUsername().trim()).isPresent()) {
			throw new ExistingResourceException("Student with username <" + student.getUsername() + "> is existing");
		}

		return studentRepo.save(student);
	}

	@Override
	public Student update(Student student) {
		logger.info("Validate object: " + student);

		validator.validate(student);

		Student newStudent = findById(student.getId());

		// Check email ton tai hay chua?
		if (studentRepo.checkNewEmail(newStudent.getId(), student.getEmail()).size() > 0) {
			throw new ExistingResourceException("User with email <" + student.getEmail() + "> is existing!");
		}

		newStudent.setFname(student.getFname().trim());
		newStudent.setLname(student.getLname().trim());
		newStudent.setDob(student.getDob());
		newStudent.setGender(student.getGender());
		newStudent.setAddress(student.getAddress().trim());
		newStudent.setEmail(student.getEmail());
		newStudent.setPhone(student.getPhone());
		newStudent.setIsDrop(student.getIsDrop());
		newStudent.setStudentClass(student.getStudentClass());

		return studentRepo.save(newStudent);
	}

	@Override
	public ResponseEntity<String> delete(Long id) {
		Student student = findById(id);

		student.setIsDrop(true);

		studentRepo.save(student);

		return ResponseEntity.ok("Delete Student with id <" + id + "> successfully !!!");
	}

	@Override
	public Student findByUsername(String username) {
		return studentRepo.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Student with username<%s> not found", username)));
	}

	@Override
	public StudentTimeTable addTimeTable(Long studentId, Integer timeTableId) {
		
		if (studentTimeTableRepo.findByStudentIdAndTimeTableId(studentId, timeTableId).isPresent()) {
			throw new ExistingResourceException("This student has registered course in time table");
		}
		Optional<TimeTable> opTimeTable = timeTableRepo.findById(timeTableId);
		if (!opTimeTable.isPresent()) {
			throw new ResourceNotFoundException("Time table with id<%d> is not exist".formatted(timeTableId));
		}
		
		StudentTimeTable timeTable = StudentTimeTable
				.builder()
				.student(findById(studentId))
				.timeTable(opTimeTable.get())
				.build();
		
		return studentTimeTableRepo.save(timeTable);
	}

	@Override
	public List<StudentTimeTable> getScheduleOfStudent(Long studentId) {
		return studentTimeTableRepo.findByStudentId(studentId);
	}
}
