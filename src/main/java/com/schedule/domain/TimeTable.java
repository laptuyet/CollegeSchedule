package com.schedule.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@ManyToOne
	@JoinColumn(name = "meetingtime_id")
	private MeetingTime meetingTime;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
