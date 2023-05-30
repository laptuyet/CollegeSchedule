package com.schedule.repo;

import com.schedule.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByUsername(String username);

    @Query("SELECT ins FROM Instructor ins WHERE ins.email = :newEmail AND ins.id <> :id")
    List<Instructor> checkNewEmail(Long id, String newEmail);
}
