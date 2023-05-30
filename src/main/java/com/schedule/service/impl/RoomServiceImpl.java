package com.schedule.service.impl;

import com.schedule.domain.Room;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.repo.RoomRepo;
import com.schedule.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Override
    public Room findById(Long number) {
        return roomRepo.findById(number).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Room with id<%s> not found", number))
        );
    }

    @Override
    public List<Room> findAll() {
        return roomRepo.findAll();
    }
}
