package com.schedule.repo;

import com.schedule.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByName(String name);
}
