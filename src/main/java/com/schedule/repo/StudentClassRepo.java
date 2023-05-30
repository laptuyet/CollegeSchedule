package com.schedule.repo;

import com.schedule.domain.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepo extends JpaRepository<StudentClass, Long> {
}
