package com.schedule.domain;

import com.schedule.model.RoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(unique = true)
    private String name;

    private int seatingCapacity;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    public Room(Long number, int seatingCapacity) {
        this.number = number;
        this.seatingCapacity = seatingCapacity;
    }
}
