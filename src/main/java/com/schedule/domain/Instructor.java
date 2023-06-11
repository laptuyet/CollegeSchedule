package com.schedule.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schedule.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User { //

    private String academicRank; // học hàm

    private String degree;       // học vị

    private Boolean isQuitJob;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<InstructorCourse> instructorCourses;
    
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<TimeTable> timeTables;

    public String toString() {
        return super.getLname();
    }
}
