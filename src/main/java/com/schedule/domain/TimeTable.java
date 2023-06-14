package com.schedule.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy = "timeTable", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StudentTimeTable> studentTimeTables;
	
	public TimeTable(
			Integer id,
			Department department,
			Course course,
			Instructor instructor,
			MeetingTime meetingTime,
			Room room
			) {
		this.id = id;
		this.department = department;
		this.course = course;
		this.instructor = instructor;
		this.meetingTime = meetingTime;
		this.room = room;
	}
	
}
