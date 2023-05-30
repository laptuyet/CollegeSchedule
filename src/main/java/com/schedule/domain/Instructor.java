package com.schedule.domain;

import com.schedule.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private List<InstructorCourse> instructorCourses;

    public String toString() {
        return super.getLname();
    }
}
