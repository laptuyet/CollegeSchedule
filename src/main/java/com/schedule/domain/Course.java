package com.schedule.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Course {

    @Id
    private String number;

    private String name;

    private int maxNumberOfStudents;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<InstructorCourse> instructorCourses;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TimeTable> timeTables;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

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
