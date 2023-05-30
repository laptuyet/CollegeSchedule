package com.schedule.repo;

import com.schedule.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);

    @Query("SELECT stu FROM Student stu WHERE stu.email = :newEmail AND stu.id <> :id")
    List<Student> checkNewEmail(Long id, String newEmail);
}
