package com.schedule.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.schedule.domain.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByUsername(String username);

    @Query("SELECT ins FROM Instructor ins WHERE ins.email = :newEmail AND ins.id <> :id")
    List<Instructor> checkNewEmail(Long id, String newEmail);
    
    @Query("""
    		select ins
    		from Instructor ins 
    		where ins.isQuitJob = false and ins.role = 'ROLE_LECTURER'
    		and ins.id in (select ic.instructor.id from InstructorCourse ic)
    		""")
    List<Instructor> findAllAvailable();
    
    List<Instructor> findByIdIn(List<Long> ids);
}
