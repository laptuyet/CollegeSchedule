package com.schedule.service;

import com.schedule.domain.Room;

import java.util.List;

public interface RoomService {

    Room findById(Long number);

    List<Room> findAll();
}
