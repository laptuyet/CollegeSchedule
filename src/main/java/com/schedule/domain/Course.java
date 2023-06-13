package com.schedule.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String number;

    private String name;

    @Min(value = 1, message = "number of students must be > 0")
    private int maxNumberOfStudents;
    
    private Boolean isDeleted;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
//    @JsonIgnore
    private List<InstructorCourse> instructorCourses;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TimeTable> timeTables;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TrainingProgram> trainingPrograms;

//    public String getNumber() {
//        return number;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Instructor> getInstructors() {
//        return instructors;
//    }
//
//    public int getMaxNumberOfStudents() {
//        return maxNumberOfStudents;
//    }

    public String toString() {
        return name;
    }
}
