package com.schedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.schedule.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    public String toString() {
        return super.getLname();
    }
}
