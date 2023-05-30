package com.schedule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.schedule.domain.Room;
import com.schedule.service.JsonMapperService;
import com.schedule.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{number}")
    public Room getRoomByNumber(@PathVariable Long number) {
        return roomService.findById(number);
    }

    @GetMapping("/all/paging")
    public List<Room> getAllRooms(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return roomService.findAll(pageNo, pageSize, sortBy);
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity
                .ok(roomService.save(room));
    }

    @PutMapping("/update")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.
                ok(roomService.update(room));
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity<String> deleteRoomByNumber (@PathVariable Long number) {
        return roomService.delete(number);
    }


}
