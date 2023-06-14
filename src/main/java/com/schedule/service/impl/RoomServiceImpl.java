package com.schedule.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.schedule.domain.Room;
import com.schedule.exception.ExistingResourceException;
import com.schedule.exception.ResourceNotFoundException;
import com.schedule.model.RoomStatus;
import com.schedule.repo.RoomRepo;
import com.schedule.service.RoomService;
import com.schedule.validator.ObjectsValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);
    private final ObjectsValidator<Room> validator;

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

    @Override
    public List<Room> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Slice<Room> slicePage = roomRepo.findAll(paging);
        return slicePage.getContent();
    }


    @Override
    public Room findByName(String name) {
        return roomRepo.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Room with name <%s> not found", name))
        );
    }

    @Override
    public Room save(Room room) {
        logger.info("Validate object: " + room);
        validator.validate(room);

        if (roomRepo.findByName(room.getName()).isPresent()) {
            throw new ExistingResourceException("Room with name <" + room.getName() + "> is existing");
        }
        return roomRepo.save(room);
    }

    @Override
    public Room update(Room room) {
        logger.info("Validate object: " + room);
        validator.validate(room);

        Room newRoom = findById(room.getNumber());

        if (roomRepo.findByName(room.getName().trim()).isPresent()) {
            throw new ExistingResourceException("Room with name <" + room.getName() + "> is existing");
        }
        if (room.getName() != null)
        	newRoom.setName(room.getName().trim());
        
        if (room.getRoomStatus() != null)
        	newRoom.setRoomStatus(room.getRoomStatus());
        
        if (room.getSeatingCapacity() != null)
        	newRoom.setSeatingCapacity(room.getSeatingCapacity());

        return roomRepo.save(newRoom);
    }

    @Override
    public ResponseEntity<String> delete(Long number) {

        Room room = findById(number);

        room.setRoomStatus(RoomStatus.DELETED);

        roomRepo.save(room);
        return ResponseEntity.ok("Delete Room with number <" + number + "> successfully !!!");
    }

	@Override
	public List<Room> findAllAvailable() {
		return roomRepo.findAllByRoomStatus(RoomStatus.ACTIVATED);
	}

}
