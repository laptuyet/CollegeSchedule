package com.schedule.repo;

import com.schedule.domain.MeetingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingTimeRepo extends JpaRepository<MeetingTime, Long> {
}
