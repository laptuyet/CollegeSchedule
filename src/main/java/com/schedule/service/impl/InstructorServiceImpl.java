package com.schedule.service.impl;

import com.schedule.domain.Instructor;
import com.schedule.exception.ExistingResourceException;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.InstructorRepo;
import com.schedule.service.InstructorService;
import com.schedule.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;
    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);
    private final ObjectsValidator<Instructor> validator;

    @Override
    public Instructor findById(Long id) {
        return instructorRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Instructor with id<%s> not found", id))
        );
    }
    
    @Override
	public Instructor findByUsername(String username) {
		return instructorRepo.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Instructor with username<%s> not found", username))
        );
	}

    @Override
    public List<Instructor> findAll() {
        return instructorRepo.findAll();
    }

    @Override
    public List<Instructor> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Slice<Instructor> slicePage = instructorRepo.findAll(paging);
        return slicePage.getContent();
    }

    @Override
    public Instructor save(Instructor instructor) {
        logger.info("Validate object: " + instructor);

        validator.validate(instructor);

        // Check username đã tồn tại hay chưa?
        if (instructorRepo.findByUsername(instructor.getUsername().trim()).isPresent()) {
            throw new ExistingResourceException("Instructor with username <" + instructor.getUsername() + "> is existing");
        }

        return instructorRepo.save(instructor);
    }

    @Override
    public Instructor update(Instructor instructor) {

        logger.info("Validate object: " + instructor);

        validator.validate(instructor);

        Instructor newInstructor = findById(instructor.getId());

        // Check email ton tai hay chua?
        if (instructorRepo.checkNewEmail(newInstructor.getId(), instructor.getEmail()).size() > 0) {
            throw new ExistingResourceException("User with email <" + instructor.getEmail() + "> is existing!");
        }

        newInstructor.setFname(instructor.getFname().trim());
        newInstructor.setLname(instructor.getLname().trim());
        newInstructor.setDob(instructor.getDob());
        newInstructor.setGender(instructor.getGender());
        newInstructor.setAddress(instructor.getAddress().trim());
        newInstructor.setEmail(instructor.getEmail());
        newInstructor.setPhone(instructor.getPhone());
        newInstructor.setAcademicRank(instructor.getAcademicRank().trim());
        newInstructor.setDegree(instructor.getDegree().trim());
        newInstructor.setIsQuitJob(instructor.getIsQuitJob());
        newInstructor.setInstructorCourses(instructor.getInstructorCourses());

        return instructorRepo.save(newInstructor);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Instructor instructor = findById(id);

        instructor.setIsQuitJob(true);

        instructorRepo.save(instructor);

        return ResponseEntity.ok("Delete Instructor with id <" + id + "> successfully !!!");
    }

	@Override
	public List<Instructor> findAllAvailable() {
		return instructorRepo.findAllByIsQuitJob(false);
	}
}
