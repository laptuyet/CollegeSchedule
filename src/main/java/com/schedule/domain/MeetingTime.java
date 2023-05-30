package com.schedule.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;

//    public MeetingTime() {}
//
//    public MeetingTime(Long id, String time) {
//        this.id = id;
//        this.time = time;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTime() {
//        return time;
//    }
}
