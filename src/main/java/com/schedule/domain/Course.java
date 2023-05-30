package com.schedule.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<InstructorCourse> instructorCourses;

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
