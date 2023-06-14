package com.schedule.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schedule.model.RoomStatus;

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

    private Integer seatingCapacity;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;
    
    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<TimeTable> timeTables;

    public Room(Long number, int seatingCapacity) {
        this.number = number;
        this.seatingCapacity = seatingCapacity;
    }
}
