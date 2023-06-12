package com.schedule.service.impl;

import com.schedule.domain.StudentClass;
import com.schedule.exception.ExistingResourceException;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.StudentClassRepo;
import com.schedule.service.StudentClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassRepo studentClassRepo;

    @Override
    public StudentClass findById(Long id) {
        return studentClassRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("StudentClass with id<%s> not found", id))
        );
    }
    
    @Override
    public StudentClass findByClassName(String className) {
    	return studentClassRepo.findByClassName(className).orElseThrow(
    			() -> new ResourceNotFoundException(String.format("StudentClass with name <%s> not found", className))
    			);
    }

    @Override
    public List<StudentClass> findAll() {
        return studentClassRepo.findAll();
    }
    
    private void checkClassNameExist(String className) {
    	 if (studentClassRepo.findByClassName(className).isPresent()) {
    		 throw new ExistingResourceException("Class with name <%s> is existed, choose another name"
    				 .formatted(className));
    	 }
    }

	@Override
	public StudentClass update(StudentClass studentClass) {
		
		StudentClass foundClass = findById(studentClass.getId());
		
		checkClassNameExist(studentClass.getClassName());
		
		if (studentClass.getClassName() != null)
			foundClass.setClassName(studentClass.getClassName());
		
		if(studentClass.getMajor() != null)
			foundClass.setMajor(studentClass.getMajor());
		
		return studentClassRepo.save(foundClass);
	}

	@Override
	public StudentClass create(StudentClass studentClass) {
		
		checkClassNameExist(studentClass.getClassName());
		
		if (studentClass.getMajor() == null) {
			throw new ResourceNotFoundException("'major' field is missing in this object");
		}
			
		return studentClassRepo.save(studentClass);
	}
}
