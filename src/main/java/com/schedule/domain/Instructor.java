package com.schedule.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schedule.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Academic Rank should not be empty")
    @NotBlank(message = "Academic Rank should not be blank")
    private String academicRank; // học hàm


    @NotEmpty(message = "Degree should not be empty")
    @NotBlank(message = "Degree should not be blank")
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
