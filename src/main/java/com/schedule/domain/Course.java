package com.schedule.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
