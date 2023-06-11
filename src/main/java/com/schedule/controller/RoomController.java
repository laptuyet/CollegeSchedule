package com.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.domain.Room;
import com.schedule.service.RoomService;

import lombok.RequiredArgsConstructor;

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

    @PreAuthorize("hasRole('ROLE_FACILITY')")
    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity
                .ok(roomService.save(room));
    }

    @PreAuthorize("hasRole('ROLE_FACILITY')")
    @PutMapping("/update")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.
                ok(roomService.update(room));
    }

    @PreAuthorize("hasRole('ROLE_FACILITY')")
    @DeleteMapping("/delete/{number}")
    public ResponseEntity<String> deleteRoomByNumber (@PathVariable Long number) {
        return roomService.delete(number);
    }


}
