package com.schedule.service;

import com.schedule.domain.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    Room findById(Long number);

    List<Room> findAll();

    List<Room> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Room save(Room objectFromJson);

    Room findByName(String name);

    Room update(Room objectFromJson);

    ResponseEntity<String> delete(Long number);
}
