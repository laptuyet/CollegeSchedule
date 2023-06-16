package com.schedule.dto;

import java.util.List;

import com.schedule.domain.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
	
	private String number;
	private String name;
	private Integer maxNumberOfStudents;
	private Boolean isDeleted;
	private List<Long> instructorsId;
	private Department department;
}
